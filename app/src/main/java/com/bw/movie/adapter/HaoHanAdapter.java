package com.bw.movie.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.HaoHan;
import java.util.ArrayList;

/**
 * date:2017/8/15
 * author:易宸锋(dell)
 * function:我完成效果使用的是,判断当前首字母和上一个条目的首字母是否一致,不一致时,就显示全部界面,一致时就隐藏第一个界面
 * 你们用多条目的思路实现一下
 */
public class HaoHanAdapter extends BaseAdapter {
    private ArrayList<HaoHan> persons =new ArrayList<>();
    private final Context context;

    public HaoHanAdapter(ArrayList<HaoHan> persons, Context context) {
        this.persons =persons;
        this.context =context;
    }

    @Override
    public int getCount() {
        return persons.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null){
            view=View.inflate(context, R.layout.item_person,null);
        } else {
            view=convertView;
        }
        TextView tv_index = (TextView) view.findViewById(R.id.tv_index);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        HaoHan haoHan = persons.get(position);
        //当前首字母
        String currentStr = haoHan.getPinyin().charAt(0) + "";
        System.out.println("当前首字母"+currentStr);
        String indexStr =null;
        //如果是第一个条目,直接显示
        if(position ==0){
            indexStr =currentStr;
        }else {
            //拿到当前首字母,上面一个条目的首字母,判断当前首字母和上一个条目的首字母是否一致,不一致时候显示完整itme界面
            String lastStr = persons.get(position - 1).getPinyin().charAt(0) + "";
            System.out.println("上面一个条目的首字母"+lastStr);
            //当前首字母和上面一个条目的首字母.判断两个是否一致,不一致就执行赋值的逻辑
            if(!TextUtils.equals(lastStr , currentStr)){
                //不一致的时候赋值indexStr
                indexStr =currentStr;
            }
        }
        tv_index.setVisibility(indexStr != null ?View.VISIBLE :View.GONE);
        tv_index.setText(currentStr);
        tv_name.setText(haoHan.getName());
        return view;
    }


    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
