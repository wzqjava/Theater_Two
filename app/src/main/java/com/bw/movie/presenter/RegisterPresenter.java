package com.bw.movie.presenter;

import android.text.TextUtils;

import com.bw.movie.activity.RegisterActivity;
import com.bw.movie.base.BaseMVPActivity;
import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.model.RegisterModel;
import com.bw.movie.view.RegisterView;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import io.reactivex.observers.DisposableObserver;

/**
 * date:2019/1/3
 * author:赵豪轩(xuan)
 * function:
 */
public class RegisterPresenter extends BaseMVPPresenter<RegisterView> {

  //  RegisterActivity extends BaseMVPActivity<RegisterView,RegisterPresenter>

    public RegisterModel mRegisterModel;

    public RegisterPresenter() {
        mRegisterModel = new RegisterModel();
    }

    public void getRegisterData(HashMap<String,String> map){
        mRegisterModel.getData(map, new DisposableObserver<RegisterBean>() {
            @Override
            public void onNext(RegisterBean registerBean) {
                //请求成功时
                if (registerBean.getMessage().equals("注册成功")){
                    //返回查询到的bean
                    view.success(registerBean);
                }else{
                    //返回错误提示
                    view.failure(registerBean.getMessage());
                }
            }
            @Override
            public void onError(Throwable e) {
                view.failure(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    public String isPass(String nickname,String sex,String birthday,String phone,String email,String pwd) {
        final String PHONE ="^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        final String PWD = "^[a-zA-Z0-9]{3,16}$";
        final String SEX="[男|女]";
        final String DATE="^\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}$";
        final String EMAIL="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if(TextUtils.isEmpty(nickname)||TextUtils.isEmpty(sex)||TextUtils.isEmpty(birthday)||
                TextUtils.isEmpty(phone)||TextUtils.isEmpty(email)||TextUtils.isEmpty(pwd)){
            return "所有输入框不能为空";
        }else{
            if(!sex.matches(SEX)){
                return "你确定你的性别?";
            }
            if(!birthday.matches(DATE)){
                return "请输入正确的日期";
            }
            if(!phone.matches(PHONE)){
                return "请输入正确格式的手机号";
            }
            if(!email.matches(EMAIL)){
                return "请输入正确的邮箱地址";
            }
            if(!pwd.matches(PWD)){
                return "请输入正确格式的密码";
            }
            return "yes";
        }
    }
}
