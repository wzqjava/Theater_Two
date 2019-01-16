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
import com.bw.movie.activity.SearchActivity;
import com.bw.movie.bean.SearchBean;
import com.bw.movie.net.Constom;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/16
 * author:李壮(大壮)
 * function:搜索查询的适配器
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private List<SearchBean.ResultBean> mResultBeans;

    public SearchAdapter(Context context) {
        mContext = context;
        mResultBeans = new ArrayList<>();
    }

    public void setData(List<SearchBean.ResultBean> resultBeans) {
        mResultBeans.clear();
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }
    /**
     * 初始化视图
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.search_recyclerview_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder viewHolder, int i) {
             viewHolder.mSearch_name.setText(mResultBeans.get(i).getName());
             viewHolder.mSearch_address.setText(mResultBeans.get(i).getAddress());
        Glide.with(mContext).load(mResultBeans.get(i).getLogo()).into(viewHolder.mSearch_icon);
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mSearch_icon;
        private final TextView mSearch_name;
        private final TextView mSearch_address;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mSearch_icon = itemView.findViewById(R.id.search_icon);
            mSearch_name = itemView.findViewById(R.id.search_name);
            mSearch_address = itemView.findViewById(R.id.search_address);
        }
    }
}
