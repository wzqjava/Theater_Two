package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.bean.RecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/4
 * author:李壮(大壮)
 * function:
 */
public class CinemaRecommendAdapter extends RecyclerView.Adapter<CinemaRecommendAdapter.ViewHolder> {

    private List<RecommendBean.ResultBean.FollowCinemasBean> mFollowCinemasBeans;
    private Context mContext;

    public CinemaRecommendAdapter(Context context) {

        mContext = context;
        mFollowCinemasBeans = new ArrayList<>();
    }


    public void setData(List<RecommendBean.ResultBean.FollowCinemasBean> followCinemasBeans) {
        mFollowCinemasBeans.clear();
        if (followCinemasBeans != null){
            mFollowCinemasBeans.addAll(followCinemasBeans);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mFollowCinemasBeans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
