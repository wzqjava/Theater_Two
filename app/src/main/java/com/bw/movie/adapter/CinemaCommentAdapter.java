package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.CinemaCommendBean;
import com.bw.movie.utils.FormatUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/11
 * author:李壮(大壮)
 * function:
 */
public class CinemaCommentAdapter extends RecyclerView.Adapter<CinemaCommentAdapter.ViewHolder> {

    private Context mContext;
    private List<CinemaCommendBean.ResultBean> mResultBeans;

    public CinemaCommentAdapter(Context context) {
        mContext = context;
        mResultBeans = new ArrayList<>();
    }

    public void setData(List<CinemaCommendBean.ResultBean> resultBeans) {
        mResultBeans.clear();
        if (resultBeans != null){
            mResultBeans.addAll(resultBeans);
        }
        notifyDataSetChanged();
    }


    public void addData(List<CinemaCommendBean.ResultBean> resultBeans) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cinema_comment_view, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(mResultBeans.get(i).getCommentHeadPic()).into(viewHolder.mCinema_recommend_user_icon);
        viewHolder.mCinema_recomment_user_name.setText(mResultBeans.get(i).getCommentUserName());
        viewHolder.mCinema_recomment_text.setText(mResultBeans.get(i).getCommentContent());
        viewHolder.mCinema_recomment_time.setText(FormatUtil.showTime(mResultBeans.get(i).getCommentTime()));
        viewHolder.mCinema_recomment_num.setText(mResultBeans.get(i).getGreatNum()+"");
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mCinema_recommend_user_icon;
        private final TextView mCinema_recomment_user_name;
        private final TextView mCinema_recomment_text;
        private final TextView mCinema_recomment_time;
        private final CheckBox mCinema_recomment_praise;
        private final TextView mCinema_recomment_num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCinema_recommend_user_icon = itemView.findViewById(R.id.cinema_recomment_user_icon);
            mCinema_recomment_praise = itemView.findViewById(R.id.cinema_recomment_praise);
            mCinema_recomment_user_name = itemView.findViewById(R.id.cinema_recomment_user_name);
            mCinema_recomment_text = itemView.findViewById(R.id.cinema_recomment_text);
            mCinema_recomment_time = itemView.findViewById(R.id.cinema_recomment_time);
            mCinema_recomment_num = itemView.findViewById(R.id.cinema_recomment_num);
        }
    }
}
