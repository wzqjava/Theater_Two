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
import com.bw.movie.activity.DeatilActivity;
import com.bw.movie.activity.PlayDetailActivity;
import com.bw.movie.bean.SelectThrastersRecyclerViewBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class SelectThreaterRecyclerviewAdapter extends RecyclerView.Adapter<SelectThreaterRecyclerviewAdapter.MyViewHolder> {

    Context context;
    List<SelectThrastersRecyclerViewBean.ResultBean> list;

    public SelectThreaterRecyclerviewAdapter(Context context, List<SelectThrastersRecyclerViewBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = View.inflate(context, R.layout.selectthraters_recyclerview_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final SelectThrastersRecyclerViewBean.ResultBean resultBean = list.get(i);
        Glide.with(myViewHolder.itemView.getContext())
                    .load(list.get(i).getLogo())
                    .into(myViewHolder.selectthraters_recyclerview_item_img);
            myViewHolder.selectthraters_recyclerview_item_name.setText(list.get(i).getName()+"");
            myViewHolder.selectthraters_recyclerview_item_xiang.setText(list.get(i).getAddress()+"");
            myViewHolder.selectthraters_recyclerview_item_km.setText(list.get(i).getId()+"km");
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EventBus.getDefault().postSticky(resultBean);
                    PlayDetailActivity.start(context);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView selectthraters_recyclerview_item_img;
        public TextView selectthraters_recyclerview_item_name;
        public TextView selectthraters_recyclerview_item_xiang;
        public TextView selectthraters_recyclerview_item_km;
        public ImageView selectthraters_recyclerview_item_guanzhu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.selectthraters_recyclerview_item_img = (ImageView) itemView.findViewById(R.id.selectthraters_recyclerview_item_img);
            this.selectthraters_recyclerview_item_name = (TextView) itemView.findViewById(R.id.selectthraters_recyclerview_item_name);
            this.selectthraters_recyclerview_item_xiang = (TextView) itemView.findViewById(R.id.selectthraters_recyclerview_item_xiang);
            this.selectthraters_recyclerview_item_km = (TextView) itemView.findViewById(R.id.selectthraters_recyclerview_item_km);
            this.selectthraters_recyclerview_item_guanzhu = (ImageView) itemView.findViewById(R.id.selectthraters_recyclerview_item_guanzhu);
        }
    }
}
