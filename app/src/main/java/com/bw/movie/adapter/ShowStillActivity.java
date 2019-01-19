package com.bw.movie.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;

import static com.alipay.sdk.data.a.i;

public class ShowStillActivity extends AppCompatActivity {

    private ImageView showstill_imageview_show;
    private ImageView showstill_imageview_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_still);
        initView();
    }

    private void initView() {
        showstill_imageview_show = (ImageView) findViewById(R.id.showstill_imageview_show);
        showstill_imageview_return = (ImageView) findViewById(R.id.showstill_imageview_return);
        showstill_imageview_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("ImageUrl");
        Glide.with(this)
                .load(imageUrl)
                .into(showstill_imageview_show);
    }
}
