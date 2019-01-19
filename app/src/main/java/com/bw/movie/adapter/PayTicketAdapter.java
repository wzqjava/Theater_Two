package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.QueryTicketBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/19
 * author:李壮(大壮)
 * function:已完成订单适配器
 */
public class PayTicketAdapter extends XRecyclerView.Adapter<PayTicketAdapter.ViewHolder> {

    private Context mContext;
    private List<QueryTicketBean.ResultBean> mResultBeans;

    public PayTicketAdapter(Context context) {
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.complete_pay_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.mPay_movie_name.setText(mResultBeans.get(i).getMovieName());
            viewHolder.mPay_beginTime.setText(mResultBeans.get(i).getBeginTime());
            viewHolder.mPay_endtime.setText(mResultBeans.get(i).getEndTime());
            viewHolder.mPay_movie_orderid.setText("订单号："+mResultBeans.get(i).getOrderId());
            viewHolder.mPay_cinema_name.setText("影院"+mResultBeans.get(i).getCinemaName());
            viewHolder.mPay_hall.setText("影厅"+mResultBeans.get(i).getScreeningHall());
            viewHolder.mPay_count.setText("数量："+mResultBeans.get(i).getAmount());
            viewHolder.mPay_sum.setText("价格:"+mResultBeans.get(i).getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mPay_movie_name;
        private final TextView mPay_beginTime;
        private final TextView mPay_movie_orderid;
        private final TextView mPay_hall;
        private final TextView mPay_cinema_name;
        private final TextView mPay_count;
        private final TextView mPay_sum;
        private final TextView mPay_endtime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPay_movie_name = itemView.findViewById(R.id.pay_movie_name);
            mPay_beginTime = itemView.findViewById(R.id.pay_begintime);
            mPay_endtime = itemView.findViewById(R.id.pay_endtime);
            mPay_movie_orderid = itemView.findViewById(R.id.pay_movie_orderid);
            mPay_cinema_name = itemView.findViewById(R.id.pay_cinema_name);
            mPay_hall = itemView.findViewById(R.id.pay_hall);
            mPay_count = itemView.findViewById(R.id.pay_count);
            mPay_sum = itemView.findViewById(R.id.pay_sum);
        }
    }
}
