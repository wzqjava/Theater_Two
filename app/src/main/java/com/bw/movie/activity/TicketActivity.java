package com.bw.movie.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;

/**
 * 展示购票记录
 */
public class TicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
    }
    public static void start(Context context) {
        context.startActivity(new Intent(context, TicketActivity.class));
    }
}
