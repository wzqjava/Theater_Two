package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.QueryTicketBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/18
 * author:李壮(大壮)
 * function:购票记录未付款适配器
 */
public class UnPayTicketAdapter extends XRecyclerView.Adapter<UnPayTicketAdapter.ViewHolder> {

    private Context mContext;
    private List<QueryTicketBean.ResultBean> mResultBeans;

    public UnPayTicketAdapter(Context context) {
        mContext = context;
        mResultBeans = new ArrayList<>();
    }

    public void setData(List<QueryTicketBean.ResultBean> resultBeans) {
        mResultBeans.clear();
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }

    public void addData(List<QueryTicketBean.ResultBean> resultBeans) {
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }

    /**
     * 初始化视图
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.unpay_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定视图
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mUbpay_movie_name.setText(mResultBeans.get(i).getMovieName());
        viewHolder.mUnpay_movie_orderid.setText("订单号："+mResultBeans.get(i).getOrderId());
        viewHolder.mUnpay_cinema_name.setText("影院："+mResultBeans.get(i).getCinemaName());
        viewHolder.mUnpay_hall.setText("影厅："+mResultBeans.get(i).getScreeningHall());
        viewHolder.mUnpay_time.setText("时间："+mResultBeans.get(i).getEndTime());
        viewHolder.mUnpay_count.setText("数量："+mResultBeans.get(i).getAmount());
        viewHolder.mUnpay_sum.setText("金额："+mResultBeans.get(i).getPrice());
        viewHolder.mUnpay_btn_go_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (mOnClickPayListener != null){
                     mOnClickPayListener.onClickPay(mResultBeans.get(i).getPrice(),mResultBeans.get(i).getOrderId());
                 }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }


    /**
     * 初始化资源控件
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mUbpay_movie_name;
        private final TextView mUnpay_cinema_name;
        private final TextView mUnpay_movie_orderid;
        private final TextView mUnpay_count;
        private final TextView mUnpay_hall;
        private final TextView mUnpay_sum;
        private final TextView mUnpay_time;
        private final Button mUnpay_btn_go_pay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mUbpay_movie_name = itemView.findViewById(R.id.ubpay_movie_name);
            mUnpay_movie_orderid = itemView.findViewById(R.id.unpay_movie_orderid);
            mUnpay_cinema_name = itemView.findViewById(R.id.unpay_cinema_name);
            mUnpay_count = itemView.findViewById(R.id.unpay_count);
            mUnpay_hall = itemView.findViewById(R.id.unpay_hall);
            mUnpay_sum = itemView.findViewById(R.id.unpay_sum);
            mUnpay_time = itemView.findViewById(R.id.unpay_time);
            mUnpay_btn_go_pay = itemView.findViewById(R.id.unpay_btn_go_pay);
        }
    }

    public interface OnClickPayListener{
        void onClickPay(double price, String orderId);
    }

    private OnClickPayListener mOnClickPayListener;

    public void setOnClickPayListener(OnClickPayListener onClickPayListener){
        mOnClickPayListener = onClickPayListener;
    }
}
