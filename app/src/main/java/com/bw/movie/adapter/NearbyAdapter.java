package com.bw.movie.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.NearbyBean;
import com.bw.movie.bean.RecommendBean;

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
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        Glide.with(mContext).load(mResultBeans.get(i).getLogo()).into(viewHolder.cinema_nearby_icon);
        viewHolder.cinema_nearby_name.setText(mResultBeans.get(i).getName());
        String address = mResultBeans.get(i).getAddress();
        address = address.substring(0,10);
        viewHolder.cinema_nearby_address.setText(address+"...");
        viewHolder.cinema_nearby_distance.setText(mResultBeans.get(i).formatDistance());

        if (mResultBeans.get(i).isFollowCinema()){
            viewHolder.mCinema_nearby_attention.setImageResource(R.mipmap.com_icon_collection_selected);
        }else {
          /*  Drawable drawable = DrawableCompat.wrap(mContext.getResources().getDrawable(R.mipmap.com_icon_cinema_selected).mutate());
            DrawableCompat.setTint(drawable, Color.GRAY);
            viewHolder.mCinema_nearby_attention.setImageDrawable(drawable);*/
            viewHolder.mCinema_nearby_attention.setImageResource(R.mipmap.com_icon_collection_default_a);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCinemaClickListener != null){
                    mCinemaClickListener.cinemaClick(mResultBeans.get(i).getId());
                }
            }
        });

        /**
         * 关注回调
         */
        viewHolder.mCinema_nearby_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCinemaAttentionClickListener != null){
                    mCinemaAttentionClickListener.cinemaAttentionClick(viewHolder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }

    public NearbyBean.ResultBean getIten(int position){
        return mResultBeans.get(position);

    }

    public void changeItenAttentionStatus(int position){
        mResultBeans.get(position).changeItenAttentionStatus();
        //notifyItemChanged(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView cinema_nearby_icon;
        private TextView cinema_nearby_name,cinema_nearby_address,cinema_nearby_distance;
        private final ImageView mCinema_nearby_attention;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cinema_nearby_icon = itemView.findViewById(R.id.cinema_nearby_icon);
            cinema_nearby_name = itemView.findViewById(R.id.cinema_nearby_name);
            cinema_nearby_address = itemView.findViewById(R.id.cinema_nearby_address);
            cinema_nearby_distance = itemView.findViewById(R.id.cinema_nearby_distance);
            mCinema_nearby_attention = itemView.findViewById(R.id.cinema_nearby_attention);
        }
    }
    public interface CinemaClickListener{
        void cinemaClick(int id);
    }

    private CinemaClickListener mCinemaClickListener;

    public void setCinemaClickListener(CinemaClickListener cinemaClickListener){
        mCinemaClickListener = cinemaClickListener;
    }

    //////////////////////////关注接口回调//////////////////////////
    public interface CinemaAttentionClickListener{
        void cinemaAttentionClick(int id);
    }

    private CinemaAttentionClickListener mCinemaAttentionClickListener;

    public void setCinemaAttentionClickListener(CinemaAttentionClickListener cinemaAttentionClickListener){
        mCinemaAttentionClickListener = cinemaAttentionClickListener;
    }
}
