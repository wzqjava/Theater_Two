package com.bw.movie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import com.bw.movie.R;
import com.bw.movie.activity.MyFragmentGuanZhuActivity;
import com.bw.movie.activity.PersonalDetailsActivity;
import com.bw.movie.activity.TicketActivity;
import com.bw.movie.bean.MyFragmentBean;

import java.util.List;

/**
 * date:2019/1/14
 * author:赵豪轩(xuan)
 * function:
 */
public class MyFragmentListViewAdapter extends BaseAdapter {

    Context context;
    List<MyFragmentBean> list;

    public MyFragmentListViewAdapter(Context context, List<MyFragmentBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MyViewHolderOne holderone = null;
        if (view == null){
            holderone = new MyViewHolderOne();
            if (getItemViewType(i) == 0){
                view = View.inflate(context,R.layout.myfragment_listview_item_one,null);
                holderone.myfragment_one = view.findViewById(R.id.myfragment_one);
                holderone.myfragment_one_two = view.findViewById(R.id.myfragment_one_two);
                holderone.myfragment_one_three = view.findViewById(R.id.myfragment_one_three);
                view.setTag(holderone);
            }else if (getItemViewType(i) == 1){
                view = View.inflate(context,R.layout.myfragment_listview_item_two,null);
                holderone.myfragment_two_one = view.findViewById(R.id.myfragment_two_one);
                holderone.myfragment_two_two = view.findViewById(R.id.myfragment_two_two);
                holderone.myfragment_two_three = view.findViewById(R.id.myfragment_two_three);
                view.setTag(holderone);
            }

            if (getItemViewType(i) == 0){
                holderone = (MyViewHolderOne) view.getTag();
                holderone.myfragment_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("zhx     zhx","1-1");
                        PersonalDetailsActivity.start(context);
                    }
                });
                holderone.myfragment_one_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("zhx     zhx","1-2");
                        MyFragmentGuanZhuActivity.start(context);
                    }
                });
                holderone.myfragment_one_three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("zhx     zhx","1-3");
                        TicketActivity.start(context);
                    }
                });
            }else if (getItemViewType(i) == 1){
                holderone = (MyViewHolderOne) view.getTag();
                holderone.myfragment_two_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("zhx     zhx","2-1");
                    }
                });
                holderone.myfragment_two_two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("zhx     zhx","2-2");
                    }
                });
                holderone.myfragment_two_three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.e("zhx     zhx","2-3");
                    }
                });
            }
        }
        return view;
    }
    class MyViewHolderOne{
        RelativeLayout myfragment_one;
        RelativeLayout myfragment_one_two;
        RelativeLayout myfragment_one_three;
        RelativeLayout myfragment_two_one;
        RelativeLayout myfragment_two_two;
        RelativeLayout myfragment_two_three;
    }
}
