package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.NearbyBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/8
 * author:李壮(大壮)
 * function:
 */
public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ViewHolder> {

    private Context mContext;
    private List<NearbyBean.ResultBean> mResultBeans;

    public NearbyAdapter(Context context) {
        mContext = context;
        mResultBeans = new ArrayList<>();
    }
    public void setData(List<NearbyBean.ResultBean> resultBeans) {
        mResultBeans.clear();
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }

    public void addData(List<NearbyBean.ResultBean> resultBeans) {
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext,R.layout.cinema_nearby_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Glide.with(mContext).load(mResultBeans.get(i).getLogo()).into(viewHolder.cinema_nearby_icon);
        viewHolder.cinema_nearby_name.setText(mResultBeans.get(i).getName());
        viewHolder.cinema_nearby_address.setText(mResultBeans.get(i).getAddress());
        viewHolder.cinema_nearby_distance.setText(mResultBeans.get(i).formatDistance());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCinemaClickListener != null){
                    mCinemaClickListener.cinemaClick(mResultBeans.get(i).getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cinema_nearby_icon;
        private TextView cinema_nearby_name,cinema_nearby_address,cinema_nearby_distance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinema_nearby_icon = itemView.findViewById(R.id.cinema_nearby_icon);
            cinema_nearby_name = itemView.findViewById(R.id.cinema_nearby_name);
            cinema_nearby_address = itemView.findViewById(R.id.cinema_nearby_address);
            cinema_nearby_distance = itemView.findViewById(R.id.cinema_nearby_distance);
        }
    }
    public interface CinemaClickListener{
        void cinemaClick(int id);
    }

    private CinemaRecommendAdapter.CinemaClickListener mCinemaClickListener;

    public void setCinemaClickListener(CinemaRecommendAdapter.CinemaClickListener cinemaClickListener){
        mCinemaClickListener = cinemaClickListener;
    }

}
