package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.axlecho.sakura.PlayerManager;
import com.axlecho.sakura.PlayerView;
import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.Detail_Detail_Bean;

import java.util.List;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class Detail_Recyclerview_Movie extends RecyclerView.Adapter<Detail_Recyclerview_Movie.MyViewHolder> {

    Context context;
    List<Detail_Detail_Bean.ResultBean.ShortFilmListBean> list;
    private MyViewHolder myViewHolder;
    private PlayerView mDetail_player_view;

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
        //mManager = myViewHolder.detail_player_view.getManager();

        String imageUrl = list.get(i).getImageUrl();
        Glide.with(context).load(imageUrl).into(myViewHolder.detail_player_img);
        myViewHolder.detail_player_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myViewHolder.detail_player_img.setVisibility(View.INVISIBLE);
                mDetail_player_view = myViewHolder.detail_player_view;
                mDetail_player_view.setVideoUrl(String.valueOf(list.get(i).getVideoUrl()));
            }
        });
    }
    public void set(){

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public PlayerView detail_player_view;
        public ImageView detail_player_img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.detail_player_view = (PlayerView) itemView.findViewById(R.id.detail_player_view);
            this.detail_player_img = (ImageView) itemView.findViewById(R.id.detail_player_img);
        }
    }
}
