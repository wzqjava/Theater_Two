package com.bw.movie.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.BuildConfig;
import com.bw.movie.R;
import com.bw.movie.app.MyApplication;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.RegisteredEventBusBean;
import com.bw.movie.greendao.UserBean;
import com.bw.movie.view.LoginInterface;
import com.bw.movie.presenter.LoginPersenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.RetrofitUtils;
import com.greendao.gen.UserBeanDao;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录页面
 */

public class LoginActivity extends BaseMVPActivity<LoginInterface,LoginPersenter> implements LoginInterface, View.OnClickListener  {

    private EditText login_edittext_phone;
    private EditText login_edittext_pwd;
    private ImageView login_imageview_showpwd;
    private CheckBox login_checkbox_rember;
    private CheckBox login_checkbox_automatic;
    private Button login_button_login;
    private TextView login_textview_registered;
    private ImageView login_button_wechat;
    private SharedPreferences rember;
    private boolean isHideFirst = true;

    @Override
    protected LoginPersenter initPresenter() {
        return new LoginPersenter();
    }

    @Override
    protected int setView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        login_edittext_phone = (EditText) findViewById(R.id.login_edittext_phone);
        login_edittext_pwd = (EditText) findViewById(R.id.login_edittext_pwd);
        login_imageview_showpwd = (ImageView) findViewById(R.id.login_imageview_showpwd);
        //login_button_wechat = (ImageView)findViewById(R.id.login_button_wechat);
        login_checkbox_rember = (CheckBox) findViewById(R.id.login_checkbox_rember);
        login_textview_registered = (TextView)findViewById(R.id.login_text_register);
        login_checkbox_automatic = (CheckBox) findViewById(R.id.login_checkbox_automatic);
        login_button_login = (Button) findViewById(R.id.login_button_login);

        login_button_login.setOnClickListener(this);
        login_imageview_showpwd.setOnClickListener(this);
        if(BuildConfig.DEBUG) {
            login_edittext_phone.setText("13793014727");
            login_edittext_pwd.setText("123456");
        }
    }

    @Override
    protected void initData() {
        rember = getSharedPreferences("Rember", MODE_PRIVATE);
        if(rember.getBoolean("rember",false)){
            login_edittext_phone.setText(rember.getString("phone",""));
            login_edittext_pwd.setText(rember.getString("pwd",""));
            login_checkbox_rember.setChecked(rember.getBoolean("rember",false));
            login_checkbox_automatic.setChecked(rember.getBoolean("automatic",false));
        }
        if(login_checkbox_automatic.isChecked()){
            Loginthis();
        }
    }

    private void Loginthis() {
        String phone = login_edittext_phone.getText().toString().trim();
        String pwd = login_edittext_pwd.getText().toString().trim();
        String notNull = presenter.DataNotNull(phone,pwd);
        if(notNull.equals("正确")){
            String encrypt = EncryptUtil.encrypt(pwd);
            Map<String,String> map = new HashMap<>();
            map.put("phone",phone);
            map.put("pwd",encrypt);
            presenter.Login(map);
        }else{
            showToast(notNull);
        }
    }

    /**
     * 记住密码
     * @param loginBean
     */
    private void remberData(LoginBean loginBean) {
        SharedPreferences.Editor edit = rember.edit();
        if(!login_checkbox_rember.isChecked()){
            edit.clear();
            edit.putString("userId", loginBean.getResult().getUserId() + "");
            edit.putString("sessionId", loginBean.getResult().getSessionId());
            edit.commit();
            return;
        }

        edit.putString("phone", login_edittext_phone.getText().toString());
        edit.putString("pwd", login_edittext_pwd.getText().toString());
        edit.putBoolean("rember", login_checkbox_rember.isChecked());
        edit.putBoolean("automatic", login_checkbox_automatic.isChecked());
        edit.putString("userId", loginBean.getResult().getUserId() + "");
        edit.putString("sessionId", loginBean.getResult().getSessionId());
        edit.commit();
    }

    /**
     * 注册成功后回显账号密码
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(RegisteredEventBusBean bean) {
        login_edittext_phone.setText(bean.getPhone());
        login_edittext_pwd.setText(bean.getPwd());
    }

    @Override
    protected void setListener() {
        login_checkbox_automatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_checkbox_rember.setChecked(login_checkbox_automatic.isChecked());
            }
        });
        login_imageview_showpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHideFirst == true) {
                    //密文

                    HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                    login_edittext_pwd.setTransformationMethod(method1);
                    isHideFirst = false;
                } else {
                    //密文
                    TransformationMethod method = PasswordTransformationMethod.getInstance();
                    login_edittext_pwd.setTransformationMethod(method);
                    isHideFirst = true;
                }
                // 光标的位置
                int index =login_edittext_pwd .getText().toString().length();
                login_edittext_pwd.setSelection(index);

            }
        });
        login_imageview_showpwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
                        login_edittext_pwd.setTransformationMethod(method1);
                        int indexs = login_edittext_pwd.getText().toString().length();
                        login_edittext_pwd.setSelection(indexs);
                        //showToast("down");
                        break;
                    case MotionEvent.ACTION_UP:
                        TransformationMethod method = PasswordTransformationMethod.getInstance();
                        login_edittext_pwd.setTransformationMethod(method);
                        int index = login_edittext_pwd.getText().toString().length();
                        login_edittext_pwd.setSelection(index);
                        //showToast("up");
                        break;
                }
                return true;
            }
        });
        login_textview_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
       /* login_button_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button_login:
                Loginthis();
                break;
        }
    }

    @Override
    public void Success(LoginBean loginBean) {
        if(loginBean != null){
            showToast(loginBean.getMessage());
            if(loginBean.isSuccess()) {

                if(login_checkbox_rember.isChecked()) {
                    remberData(loginBean);
                }

                RetrofitUtils.getInstance().updateUserId(
                        String.valueOf(loginBean.getResult().getUserId()),
                        loginBean.getResult().getSessionId());

                UserBeanDao userBeanDao =
                        MyApplication.getInstances().getDaoSession().getUserBeanDao();

                UserBean userBean = new UserBean(loginBean.getResult().getSessionId()+"",loginBean.getResult().getUserId()+"",loginBean.getResult().getUserInfo().getBirthday()+"",
                        loginBean.getResult().getUserInfo().getId()+"",loginBean.getResult().getUserInfo().getLastLoginTime()+"",loginBean.getResult().getUserInfo().getNickName()+"",
                        loginBean.getResult().getUserInfo().getPhone()+"",loginBean.getResult().getUserInfo().getSex()+"",loginBean.getResult().getUserInfo().getHeadPic()+"");

                Log.e("zhx",loginBean.getResult().getUserInfo().getNickName()+"");
                List<UserBean> userBeans = userBeanDao.loadAll();
                if (userBeans.size() != 0){
                    userBeanDao.deleteAll();
                }else{
                }
                userBeanDao.insert(userBean);


                List<UserBean> userBeans1 = userBeanDao.loadAll();
                Log.e("zhx",userBeans1.size()+"");


                Intent intent = new Intent(LoginActivity.this,ShelfActivity.class);
                startActivity(intent);
            } else {
                //
            }
        }
        /*if (loginBean != null) {
            showToast(loginBean.getMessage());
            if (loginBean.getMessage().equals("登陆成功")) {
                remberData(loginBean);
                startActivity(new Intent(LoginActivity.this,ShelfActivity.class));
                finish();
            }
        }*/
    }

    @Override
    public void Failed() {

    }

}
