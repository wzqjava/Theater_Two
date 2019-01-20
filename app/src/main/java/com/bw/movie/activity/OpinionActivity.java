package com.bw.movie.activity;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;

public class OpinionActivity extends BaseActivity implements View.OnClickListener {

    private EditText opinion_edittext_content;
    private Button opinion_button_commit;
    private LinearLayout opinion_relativeLayout_showhide;
    private ImageView opinion_imageview_return;

    @Override
    protected int setView() {
        return R.layout.activity_opinion;
    }

    public void initView() {
        opinion_edittext_content = (EditText) findViewById(R.id.opinion_edittext_content);
        opinion_button_commit = (Button) findViewById(R.id.opinion_button_commit);
        opinion_relativeLayout_showhide = (LinearLayout) findViewById(R.id.opinion_relativeLayout_showhide);
        opinion_imageview_return = (ImageView) findViewById(R.id.opinion_imageview_return);

        opinion_button_commit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {
        opinion_imageview_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opinion_relativeLayout_showhide.isShown()){
                    showToast("感谢您的支持");
                }
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.opinion_button_commit:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String content = opinion_edittext_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showToast("写下您对产品的感受吧！工作人员将会在第一时间评估处理。");
            return;
        }
        if(content.length()<10){
            showToast("反馈信息太短喽");
            return;
        }
        opinion_edittext_content.setVisibility(View.GONE);
        opinion_button_commit.setVisibility(View.GONE);
        opinion_relativeLayout_showhide.setVisibility(View.VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(opinion_relativeLayout_showhide, "alpha", 0f, 1f);
        animator.setDuration(1000);
        animator.start();

    }
}
