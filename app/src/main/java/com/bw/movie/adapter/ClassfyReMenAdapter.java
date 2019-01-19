package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
import com.bw.movie.view.ClassfyGuanZhuView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.internal.operators.flowable.FlowableUnsubscribeOn;

/**
 * date:2019/1/7
 * author:赵豪轩(xuan)
 * function:
 */
public class ClassfyReMenAdapter extends RecyclerView.Adapter<ClassfyReMenAdapter.MyViewHolder> {

    Context context;
    public List<MovieFragmentBean.ResultBean> list;

    public ClassfyReMenAdapter(Context context, List<MovieFragmentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.classfy_recyclerview_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.classfy_recyclerview_item_name.setText(list.get(i).getName() + "");
        String summary = list.get(i).getSummary();
        summary = summary.substring(0,100);
        myViewHolder.classfy_recyclerview_item_context.setText(summary + "");
        Glide.with(context).load(list.get(i).getImageUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(8)))
                .into(myViewHolder.classfy_recyclerview_item_img);

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieFragmentBean.ResultBean resultBean = list.get(i);
                DeatilActivity.start(context);
                EventBus.getDefault().postSticky(resultBean);
            }
        });
        if (list.get(i).getFollowMovie() == 1){
            myViewHolder.classfy_recyclerview_item_guanzhu.setImageResource(R.mipmap.com_icon_collection_selected);
        }else{
            myViewHolder.classfy_recyclerview_item_guanzhu.setImageResource(R.mipmap.com_icon_collection_default);
        }
        myViewHolder.classfy_recyclerview_item_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int followMovie = list.get(i).getFollowMovie();
                    if (followMovie == 1){
                    myViewHolder.classfy_recyclerview_item_guanzhu.setImageResource(R.mipmap.com_icon_collection_default);
                        if (mClassfyGuanZhuView != null){
                            mClassfyGuanZhuView.quXiao(list.get(i).getId()+"",i+"");
                        }
                }else{
                    myViewHolder.classfy_recyclerview_item_guanzhu.setImageResource(R.mipmap.com_icon_collection_selected);
                    if (mClassfyGuanZhuView != null){
                        mClassfyGuanZhuView.guanZhu(list.get(i).getId()+"",i+"");
                    }
                }
            }
        });
    }
    ClassfyGuanZhuView mClassfyGuanZhuView;
    public void setClassfyGuanZhuView(ClassfyGuanZhuView classfyGuanZhuView){
        mClassfyGuanZhuView = classfyGuanZhuView;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView classfy_recyclerview_item_img;
        public ConstraintLayout con;
        public TextView classfy_recyclerview_item_name;
        public TextView classfy_recyclerview_item_context;
        public ImageView classfy_recyclerview_item_guanzhu;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.classfy_recyclerview_item_img = (ImageView) itemView.findViewById(R.id.classfy_recyclerview_item_img);
            this.con = (ConstraintLayout) itemView.findViewById(R.id.con);
            this.classfy_recyclerview_item_name = (TextView) itemView.findViewById(R.id.classfy_recyclerview_item_name);
            this.classfy_recyclerview_item_context = (TextView) itemView.findViewById(R.id.classfy_recyclerview_item_context);
            this.classfy_recyclerview_item_guanzhu = (ImageView) itemView.findViewById(R.id.classfy_recyclerview_item_guanzhu);

        }
    }
}
