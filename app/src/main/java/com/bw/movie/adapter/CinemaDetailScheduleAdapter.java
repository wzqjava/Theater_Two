package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaDetailScheduleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/1/10
 * author:李壮(大壮)
 * function:  影院当前影片排期情况
 */
public class CinemaDetailScheduleAdapter extends RecyclerView.Adapter<CinemaDetailScheduleAdapter.ViewHolder> {

    private Context mContext;
    private List<CinemaDetailScheduleBean.ResultBean> mResultBeans;

    public CinemaDetailScheduleAdapter(Context context) {
        mContext = context;
        mResultBeans = new ArrayList<>();
    }
    public void setData(List<CinemaDetailScheduleBean.ResultBean> cinemaDetailScheduleBeans) {
        mResultBeans.clear();
        if (cinemaDetailScheduleBeans != null){
            mResultBeans.addAll(cinemaDetailScheduleBeans);
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.cinema_detail_schedule_item, viewGroup, false);
       // View view = View.inflate(mContext, R.layout.cinema_detail_schedule_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.mCinema_detail_schedule_office.setText(mResultBeans.get(i).getScreeningHall());
        viewHolder.mCinema_detail_schedule_start.setText(mResultBeans.get(i).getBeginTime());
        viewHolder.mCinema_detail_schedule_end.setText(mResultBeans.get(i).getEndTime());
        viewHolder.mCinema_detail_schedule_price.setText(mResultBeans.get(i).getPrice()+"");

        viewHolder.mCinema_detail_schedule_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSetOnClickChooseListener != null){
                    mSetOnClickChooseListener.onClick(mResultBeans.get(i).getId(),
                            mResultBeans.get(i).getScreeningHall(),
                            mResultBeans.get(i).getBeginTime(),
                            mResultBeans.get(i).getEndTime(),
                            mResultBeans.get(i).getPrice()+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mCinema_detail_schedule_office;
        private final TextView mCinema_detail_schedule_start;
        private final TextView mCinema_detail_schedule_end;
        private final TextView mCinema_detail_schedule_price;
        private final ImageView mCinema_detail_schedule_next;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCinema_detail_schedule_office = itemView.findViewById(R.id.cinema_detail_schedule_office);
            mCinema_detail_schedule_start = itemView.findViewById(R.id.cinema_detail_schedule_start);
            mCinema_detail_schedule_end = itemView.findViewById(R.id.cinema_detail_schedule_end);
            mCinema_detail_schedule_price = itemView.findViewById(R.id.cinema_detail_schedule_price);
            mCinema_detail_schedule_next = itemView.findViewById(R.id.cinema_detail_schedule_next);
        }
    }

    public interface SetOnClickChooseListener{
        void onClick(int id, String screeningHall, String beginTime, String endTime, String price);
    }

    private SetOnClickChooseListener mSetOnClickChooseListener;

    public void setSetOnClickListener(SetOnClickChooseListener setOnClickChooseListener) {
        mSetOnClickChooseListener = setOnClickChooseListener;
    }
}
