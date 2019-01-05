package com.bw.movie.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.MainActivity;
import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.presenter.RegisterPresenter;
import com.bw.movie.utils.Base64;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.view.RegisterView;

import java.util.HashMap;

/**
 * date:2019/1/3
 * author:赵豪轩(xuan)
 * function:
 */
public class RegisterActivity extends BaseMVPActivity<RegisterView, RegisterPresenter> implements RegisterView{
    private EditText register_name;
    private EditText register_sex;
    private EditText register_date;
    private EditText register_phone;
    private EditText register_mail;
    private EditText register_lock;
    private Button register_register;

    /**
     * 初始化presenter
     *
     * @return
     */
    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    /**
     * 设置布局
     *
     * @return
     */
    @Override
    protected int setView() {
        return R.layout.activity_register;
    }

    /**
     * 初始化View
     */
    @Override
    protected void initView() {
        register_name = findViewById(R.id.register_name);
        register_sex = findViewById(R.id.register_sex);
        register_date = findViewById(R.id.register_date);
        register_phone = findViewById(R.id.register_phone);
        register_mail = findViewById(R.id.register_mail);
        register_lock = findViewById(R.id.register_lock);
        register_register = findViewById(R.id.register_register);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {

    }

    /**
     * 设置监听
     */
    @Override
    protected void setListener() {
        //点击生日
        register_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("输入的格式为：1998-01-28！！！");
                /* final View popup_item = View.inflate(RegisterActivity.this, R.layout.register_popupwindow, null);
                PopupWindow window = new PopupWindow(popup_item, 1000, 300, true);
                window.setBackgroundDrawable(new ColorDrawable());//设置背景
                window.setOutsideTouchable(true);//
                window.setTouchable(true);//
                window.showAsDropDown(register_sex);//弹出的位置在menu的下面*/
            }
        });
        register_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("输入内容为1或2，1为男士，2为女士！！！");
            }
        });
        //用户点击注册时
        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = register_name.getText().toString().trim();
                String sex = register_sex.getText().toString().trim();
                String date = register_date.getText().toString().trim();
                String phone = register_phone.getText().toString().trim();
                String mail = register_mail.getText().toString().trim();
                //密码
                String lock = register_lock.getText().toString().trim();
                //正则验证手机号
                boolean chinaPhoneLegal = presenter.isChinaPhoneLegal(phone);
                if (chinaPhoneLegal) {
                    //密码使用AES去加密
                    String decrypt = EncryptUtil.encrypt(lock);
                    //创建集合，存放参数
                    HashMap<String, String> map = new HashMap<>();
                    //添加参数
                    map.put("nickName",name);
                    map.put("phone",phone);
                    map.put("pwd",decrypt);
                    map.put("pwd2",decrypt);
                    map.put("sex",sex);
                    map.put("birthday",date);
                    map.put("imei","1");
                    map.put("ua","锤子");
                    map.put("screenSize","5.0");
                    map.put("os","android");
                    map.put("email",mail);
                    //去进行网络请求
                    presenter.getRegisterData(map);
                    Log.e("zhx",name+"         "+sex+"         "+date+"         "+phone+"         "+mail+"         "+lock+"         "+decrypt);
                }else{
                    showToast("您输入的手机号格式错误");
                    Log.e("zhx","您输入的手机号格式错误");
                }
            }
        });
    }

    @Override
    public void success(RegisterBean registerBean) {


        showToast(registerBean.getMessage()+"");
       /* Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();*/
    }
    @Override
    public void failure(String msg) {
        showToast(msg+"");
    }

}
