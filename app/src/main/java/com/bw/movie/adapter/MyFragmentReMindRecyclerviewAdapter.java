package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.MyFragmentReMindRecyclerViewBean;

import java.util.List;

/**
 * date:2019/1/9
 * author:赵豪轩(xuan)
 * function:
 */
public class MyFragmentReMindRecyclerviewAdapter extends RecyclerView.Adapter<MyFragmentReMindRecyclerviewAdapter.MyViewHolder> {
    Context context;
    List<MyFragmentReMindRecyclerViewBean.ResultBean> list;

    public MyFragmentReMindRecyclerviewAdapter(Context context,List<MyFragmentReMindRecyclerViewBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.myfragment_remindactivity_recyclerview_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.myfragment_remind_item_title.setText(list.get(i).getTitle()+"");
        myViewHolder.myfragment_remind_item_content.setText(list.get(i).getContent()+"");
        String date = (String)DateFormat.format("yyyy年 MMMM dd日  kk:mm", list.get(i).getPushTime());
        myViewHolder.myfragment_remind_item_date.setText(date  + "");
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView myfragment_remind_item_title;
        public TextView myfragment_remind_item_content;
        public TextView myfragment_remind_item_date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.myfragment_remind_item_title = (TextView) itemView.findViewById(R.id.myfragment_remind_item_title);
            this.myfragment_remind_item_content = (TextView) itemView.findViewById(R.id.myfragment_remind_item_content);
            this.myfragment_remind_item_date = (TextView) itemView.findViewById(R.id.myfragment_remind_item_date);
        }
    }
}
