package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.axlecho.sakura.PlayerManager;
import com.axlecho.sakura.PlayerView;
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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        //mManager = myViewHolder.detail_player_view.getManager();
        myViewHolder.detail_player_view.setVideoUrl(String.valueOf(list.get(i).getVideoUrl()));
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public PlayerView detail_player_view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.detail_player_view = (PlayerView) itemView.findViewById(R.id.detail_player_view);
        }
    }
}
