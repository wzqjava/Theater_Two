package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DeatilActivity;
import com.bw.movie.bean.MovieFragmentBean;
import com.bw.movie.fragment.MovieFregment;
import com.bw.movie.view.MovieFregmentView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * date:2019/1/7
 * author:赵豪轩(xuan)
 * function:
 */
public class MovieFragmentReMenAdapter extends RecyclerView.Adapter<MovieFragmentReMenAdapter.MyViewHolder> {

    Context context;
    List<MovieFragmentBean.ResultBean> list;

    public MovieFragmentReMenAdapter(Context context, List<MovieFragmentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.moviefragment_remen_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.moviefragment_recyclerview_remen_item_name.setText(list.get(i).getName()+"");
        Glide.with(context).load(list.get(i).getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(myViewHolder.moviefragment_recyclerview_remen_item_img);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              MovieFragmentBean.ResultBean resultBean = list.get(i);
              DeatilActivity.start(context);
              EventBus.getDefault().postSticky(resultBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView moviefragment_recyclerview_remen_item_img;
        public TextView moviefragment_recyclerview_remen_item_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.moviefragment_recyclerview_remen_item_img = (ImageView) itemView.findViewById(R.id.moviefragment_recyclerview_remen_item_img);
            this.moviefragment_recyclerview_remen_item_name = (TextView) itemView.findViewById(R.id.moviefragment_recyclerview_remen_item_name);
        }
    }
}
