package com.bw.movie.presenter;

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
    public boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
