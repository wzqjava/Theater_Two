package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.activity.DeatilActivity;
import com.bw.movie.bean.DetailPingLunBean;
import com.bw.movie.utils.GlideRoundedCornersUtil;

import java.util.List;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class DetailRecyclerviewPingLunAdapter extends RecyclerView.Adapter<DetailRecyclerviewPingLunAdapter.MyViewHolder> {

    Context context;
    List<DetailPingLunBean.ResultBean> list;

    public DetailRecyclerviewPingLunAdapter(DeatilActivity context, List<DetailPingLunBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = View.inflate(context, R.layout.detail_recyclerview_pinglun_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        RequestManager with = Glide.with(context);
        with.load(list.get(i).getCommentHeadPic())
                .apply(new RequestOptions().transform(new CircleCrop()))
                .into(myViewHolder.detail_pinglun_img);
       /* Glide.with(myViewHolder.itemView.getContext())


             // .transform(new GlideRoundedCornersUtil(context,50))

                .load()
                .into(myViewHolder.detail_pinglun_img);*/



        myViewHolder.detail_pinglun_name.setText(list.get(i).getCommentUserName()+"");
        myViewHolder.detail_pinglun_xiangqing.setText(list.get(i).getCommentContent()+"");
        myViewHolder.detail_pinglun_zannum.setText(list.get(i).getGreatNum()+"");
        myViewHolder.detail_pinglun_pinglunnum.setText(list.get(i).getReplyNum()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView detail_pinglun_img;
        public TextView detail_pinglun_name;
        public TextView detail_pinglun_xiangqing;
        public ImageView detail_pinglun_zan;
        public TextView detail_pinglun_zannum;
        public ImageView detail_pinglun_pinglun;
        public TextView detail_pinglun_pinglunnum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.detail_pinglun_img = (ImageView) itemView.findViewById(R.id.detail_pinglun_img);
            this.detail_pinglun_name = (TextView) itemView.findViewById(R.id.detail_pinglun_name);
            this.detail_pinglun_xiangqing = (TextView) itemView.findViewById(R.id.detail_pinglun_xiangqing);
            this.detail_pinglun_zan = (ImageView) itemView.findViewById(R.id.detail_pinglun_zan);
            this.detail_pinglun_zannum = (TextView) itemView.findViewById(R.id.detail_pinglun_zannum);
            this.detail_pinglun_pinglun = (ImageView) itemView.findViewById(R.id.detail_pinglun_pinglun);
            this.detail_pinglun_pinglunnum = (TextView) itemView.findViewById(R.id.detail_pinglun_pinglunnum);
        }
    }

}
