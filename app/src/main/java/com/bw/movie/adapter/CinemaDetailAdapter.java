package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaDetailIconBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/9
 * author:李壮(大壮)
 * function:影院详情页面适配器
 */
public class CinemaDetailAdapter extends RecyclerView.Adapter<CinemaDetailAdapter.ViewHolder> {

    private Context mContext;
    private List<CinemaDetailIconBean.ResultBean> mResultBean;

    public CinemaDetailAdapter(Context context) {
        mContext = context;
        mResultBean = new ArrayList<>();
    }

    public CinemaDetailIconBean.ResultBean getItem(int position) {
        return mResultBean.get(position);
    }

    public void setData(List<CinemaDetailIconBean.ResultBean> result) {
        mResultBean.clear();
        if (result != null){
            mResultBean.addAll(result);
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
        View view = View.inflate(mContext, R.layout.cinema_detail_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * 绑定视图
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(mResultBean.get(i).getImageUrl()).into(viewHolder.mCinema_detail_icon);
        viewHolder.mCinema_detail_name.setText(mResultBean.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return mResultBean.size();
    }

    /**
     * 获取资源id控件
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mCinema_detail_icon;
        private final TextView mCinema_detail_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCinema_detail_icon = itemView.findViewById(R.id.cinema_detail_icon);
            mCinema_detail_name = itemView.findViewById(R.id.cinema_detail_name);
        }
    }
}
