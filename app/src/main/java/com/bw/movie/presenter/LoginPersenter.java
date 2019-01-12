package com.bw.movie.presenter;

import android.text.TextUtils;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.model.LoginModel;
import com.bw.movie.view.LoginInterface;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

public class LoginPersenter extends BaseMVPPresenter<LoginInterface> {

    private final LoginModel mLoginModel;

    public LoginPersenter(){
        mLoginModel = new LoginModel();
    }

    public void Login(Map<String,String > map){
        mLoginModel.Login(map,new DisposableObserver<LoginBean>() {
            @Override
            public void onNext(LoginBean loginBean) {
                view.Success(loginBean);
            }

            @Override
            public void onError(Throwable e) {
                view.Failed();
                onComplete();
            }

            @Override
            public void onComplete() {
                //view.Failed();
                //
            }
        });
    }

    public String DataNotNull(String phone, String pwd) {

        final String REGEX_MOBILE ="^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        final String REGEX_PASSWORD = "^[a-zA-Z0-9]{3,16}$";
        // validate
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
            if (phone.matches(REGEX_MOBILE) && pwd.matches(REGEX_PASSWORD)) {
                return "正确";
            } else {
                return "账号密码格式错误";
            }
        } else {
            return "账号密码必填";
        }
    }
}
