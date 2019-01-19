package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.DeatilActivity;
import com.bw.movie.bean.PlayDetailPaiQiRecyclerViewBean;
import com.bw.movie.view.PlayDetailIdView;

import java.util.List;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class PlayDetailRecyclerviewPaiQi extends RecyclerView.Adapter<PlayDetailRecyclerviewPaiQi.MyViewHolder> {

    Context context;
    List<PlayDetailPaiQiRecyclerViewBean.ResultBean> list;

    public PlayDetailRecyclerviewPaiQi(Context context, List<PlayDetailPaiQiRecyclerViewBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = View.inflate(context, R.layout.playdetail_recyclerview_paiqi_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.playdetail_recyclerview_item_ting.setText(list.get(i).getScreeningHall()+"");
        TextPaint paint =
                myViewHolder.playdetail_recyclerview_item_begin.getPaint();
        paint.setFakeBoldText(true);
        myViewHolder.playdetail_recyclerview_item_begin.setText(list.get(i).getBeginTime()+"__");
        myViewHolder.playdetail_recyclerview_item_over.setText(list.get(i).getEndTime()+" end");
        myViewHolder.playdetail_recyclerview_item_price.setText("￥:"+list.get(i).getPrice()+"");

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayDetailIdView != null){
                    mPlayDetailIdView.success(""+list.get(i).getId(),list.get(i));
                }
            }
        });
    }
    PlayDetailIdView mPlayDetailIdView;
    public void setPlayDetailIdView(PlayDetailIdView playDetailIdView) {
        mPlayDetailIdView = playDetailIdView;
    }    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView playdetail_recyclerview_item_ting;
        public TextView playdetail_recyclerview_item_begin;
        public TextView playdetail_recyclerview_item_over;
        public TextView playdetail_recyclerview_item_price;
        public ImageView playdetail_recyclerview_item_img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playdetail_recyclerview_item_ting = (TextView) itemView.findViewById(R.id.playdetail_recyclerview_item_ting);
            this.playdetail_recyclerview_item_begin = (TextView) itemView.findViewById(R.id.playdetail_recyclerview_item_begin);
            this.playdetail_recyclerview_item_over = (TextView) itemView.findViewById(R.id.playdetail_recyclerview_item_over);
            this.playdetail_recyclerview_item_price = (TextView) itemView.findViewById(R.id.playdetail_recyclerview_item_price);
            this.playdetail_recyclerview_item_img = (ImageView) itemView.findViewById(R.id.playdetail_recyclerview_item_img);
        }
    }
}
