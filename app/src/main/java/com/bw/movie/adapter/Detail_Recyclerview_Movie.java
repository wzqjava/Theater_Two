package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.Detail_Detail_Bean;
import com.bw.movie.utils.GlideRoundedCornersUtil;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class Detail_Recyclerview_Movie extends RecyclerView.Adapter<Detail_Recyclerview_Movie.MyViewHolder> {

    Context context;
    List<Detail_Detail_Bean.ResultBean.ShortFilmListBean> list;
    private MyViewHolder myViewHolder;
    private JZVideoPlayer mDetail_player_view;

    public Detail_Recyclerview_Movie(Context context, List<Detail_Detail_Bean.ResultBean.ShortFilmListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = View.inflate(context, R.layout.detail_recyclerview_movie_item, null);
        myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.detail_player_view.setUp(list.get(i).getVideoUrl(),JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                "预告片");
        Glide.with(context).load(list.get(i).getImageUrl())
                .into(myViewHolder.detail_player_view.thumbImageView);
    }
    public void set(){

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public JZVideoPlayerStandard detail_player_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.detail_player_view = (JZVideoPlayerStandard) itemView.findViewById(R.id.detail_player_view);
        }
    }
}
