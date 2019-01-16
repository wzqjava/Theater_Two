package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.greendao.UserBean;
import com.greendao.gen.UserBeanDao;

import java.util.List;

public class PersonalDetailsActivity extends AppCompatActivity {

    private ImageView personaldetail_activity_img;
    private TextView personaldetail_activity_name;
    private TextView personaldetail_activity_sex;
    private TextView personaldetail_activity_date;
    private TextView personaldetail_activity_phone;
    private TextView personaldetail_activity_email;
    private ImageView personaldetail_activity_fanhui;
    private UserBeanDao mUserBeanDao;
    private List<UserBean> mUserBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        initView();


        mUserBeanDao = MyApplication.getInstances().getDaoSession().getUserBeanDao();
        mUserBeans = mUserBeanDao.loadAll();

        personaldetail_activity_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        personaldetail_activity_name.setText(mUserBeans.get(0).getNickName()+"");
        personaldetail_activity_sex.setText(mUserBeans.get(0).getSex()+"");
          String date = (String)DateFormat.format("yyyy年 MMMM dd日", Long.parseLong(mUserBeans.get(0).getLastLoginTime()+""));
        // myViewHolder.myfragment_remind_item_date.setText(date  + "");
         personaldetail_activity_date.setText(date  + "");
        personaldetail_activity_phone.setText(mUserBeans.get(0).getPhone()+"");


    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, PersonalDetailsActivity.class));
    }

    private void initView() {

        personaldetail_activity_img = (ImageView) findViewById(R.id.personaldetail_activity_img);
        personaldetail_activity_name = (TextView) findViewById(R.id.personaldetail_activity_name);
        personaldetail_activity_sex = (TextView) findViewById(R.id.personaldetail_activity_sex);
        personaldetail_activity_date = (TextView) findViewById(R.id.personaldetail_activity_date);
        personaldetail_activity_phone = (TextView) findViewById(R.id.personaldetail_activity_phone);
        personaldetail_activity_email = (TextView) findViewById(R.id.personaldetail_activity_email);
        personaldetail_activity_fanhui = (ImageView) findViewById(R.id.personaldetail_activity_fanhui);
    }
}
