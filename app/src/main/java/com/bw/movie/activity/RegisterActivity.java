package com.bw.movie.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bw.movie.R;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.RegisteredEventBusBean;
import com.bw.movie.presenter.RegisterPresenter;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.view.RegisterView;

import org.greenrobot.eventbus.EventBus;

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
    private String phone;
    private String pwd;

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
        register_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            register_date.setText(String.format("%d-%d-%d",year,month+1,dayOfMonth));
                        }
                    },2018,1,5).show();
                }
            }
        });
        //用户点击注册时
        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = register_name.getText().toString().trim();
                String sex = register_sex.getText().toString().trim();
                String date = register_date.getText().toString().trim();
                phone = register_phone.getText().toString().trim();
                String mail = register_mail.getText().toString().trim();
                //密码
                pwd = register_lock.getText().toString().trim();
                //正则验证手机号
                String isPass = presenter.isPass(name,sex,date,phone,mail,pwd);
                if (isPass.equals("yes")) {
                    //密码使用AES去加密
                    String decrypt = EncryptUtil.encrypt(pwd);
                    //创建集合，存放参数
                    HashMap<String, String> map = new HashMap<>();
                    //添加参数
                    map.put("nickName",name);
                    map.put("phone", phone);
                    map.put("pwd",decrypt);
                    map.put("pwd2",decrypt);
                    map.put("sex",(sex.equals("男")?1:2)+"");
                    map.put("birthday",date);
                    map.put("imei","1");
                    map.put("ua","锤子");
                    map.put("screenSize","5.0");
                    map.put("os","android");
                    map.put("email",mail);
                    //去进行网络请求
                    presenter.getRegisterData(map);
                    Log.e("zhx",name+"         "+sex+"         "+date+"         "+ phone +"         "+mail+"         "+ pwd +"         "+decrypt);
                }else{
                    showToast(isPass);
                    Log.e("zhx","您输入的手机号格式错误");
                }
            }
        });
    }

    @Override
    public void success(RegisterBean registerBean) {

        showToast(registerBean.getMessage()+"");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        EventBus.getDefault().postSticky(new RegisteredEventBusBean(phone,pwd));
        finish();
    }
    @Override
    public void failure(String msg) {
        showToast(msg+"");
    }

    public void Finish(View view){
        finish();
    }

}
