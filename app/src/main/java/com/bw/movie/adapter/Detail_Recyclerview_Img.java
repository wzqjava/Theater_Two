package com.bw.movie.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.axlecho.sakura.PlayerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DeatilActivity;

import java.util.List;

import static com.bumptech.glide.request.target.Target.SIZE_ORIGINAL;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class Detail_Recyclerview_Img extends RecyclerView.Adapter<Detail_Recyclerview_Img.MyViewHolder> {

    Context context;
    List<String> list;

    public Detail_Recyclerview_Img(DeatilActivity context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = View.inflate(context, R.layout.detail_recyclerview_img_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Glide.with(myViewHolder.itemView.getContext())
                .load(list.get(i))
                .into(myViewHolder.detail_img_view);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView detail_img_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.detail_img_view = (ImageView) itemView.findViewById(R.id.detail_img_view);
        }
    }
}
