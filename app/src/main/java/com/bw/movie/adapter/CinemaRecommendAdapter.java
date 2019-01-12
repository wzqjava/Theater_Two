package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.RecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/4
 * author:李壮(大壮)
 * function:
 */
public class CinemaRecommendAdapter extends RecyclerView.Adapter<CinemaRecommendAdapter.ViewHolder> {

    private List<RecommendBean.ResultBean> mResultBeans;
    private Context mContext;

    public CinemaRecommendAdapter(Context context) {

        mContext = context;
        mResultBeans = new ArrayList<>();
    }


    public void setData(List<RecommendBean.ResultBean> resultBeans) {
        mResultBeans.clear();
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }

    public void addData(List<RecommendBean.ResultBean> resultBeans) {
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.cinema_recommend_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(mContext).load(mResultBeans.get(i).getLogo()).into(viewHolder.cinema_recommend_icon);
        viewHolder.cinema_recommend_name.setText(mResultBeans.get(i).getName());
        viewHolder.cinema_recommend_address.setText(mResultBeans.get(i).getAddress());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCinemaClickListener != null){
                    mCinemaClickListener.cinemaClick(mResultBeans.get(i).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView cinema_recommend_icon;
        private TextView cinema_recommend_name,cinema_recommend_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinema_recommend_icon = itemView.findViewById(R.id.cinema_recommend_icon);
            cinema_recommend_name = itemView.findViewById(R.id.cinema_recommend_name);
            cinema_recommend_address = itemView.findViewById(R.id.cinema_recommend_address);
        }
    }

    public interface CinemaClickListener{
        void cinemaClick(int id);
    }

    private CinemaClickListener mCinemaClickListener;

    public void setCinemaClickListener(CinemaClickListener cinemaClickListener){
        mCinemaClickListener = cinemaClickListener;
    }

}
