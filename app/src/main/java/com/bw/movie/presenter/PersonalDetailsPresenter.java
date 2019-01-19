package com.bw.movie.presenter;

import android.telephony.MbmsDownloadSession;
import android.text.TextUtils;
import android.util.Log;

import com.bw.movie.base.BaseMVPPresenter;
import com.bw.movie.bean.PingLunPingLunBean;
import com.bw.movie.bean.ShangChuanHeadPicBean;
import com.bw.movie.bean.UserIDChaXunBean;
import com.bw.movie.model.PersonalDetailsModel;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.view.PersonalDetailsView;

import java.io.File;
import java.util.HashMap;

import io.reactivex.observers.DisposableObserver;
import okhttp3.RequestBody;

/**
 * date:2019/1/18
 * author:赵豪轩(xuan)
 * function:
 */
public class PersonalDetailsPresenter extends BaseMVPPresenter<PersonalDetailsView>{

    PersonalDetailsModel mPersonalDetailsModel;

    public PersonalDetailsPresenter() {
        mPersonalDetailsModel = new PersonalDetailsModel();
    }

    public void getChaXun(String userId, String sessionId) {
        mPersonalDetailsModel.getChaXunData(userId,sessionId, new DisposableObserver<UserIDChaXunBean>() {
            @Override
            public void onNext(UserIDChaXunBean bean) {
                if (bean != null){
                    if (bean.getStatus().equals("0000")){
                        UserIDChaXunBean.ResultBean result = bean.getResult();
                        view.ChaXunSuccess(result);
                    }else {
                        view.ChaXunError(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                view.ChaXunError(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }

    public void getShangChuanHeadPic(String userId, String sessionId, HashMap<String, RequestBody> map) {
        mPersonalDetailsModel.getShangChuanHeadPicData(userId,sessionId,map, new DisposableObserver<ShangChuanHeadPicBean>() {
            @Override
            public void onNext(ShangChuanHeadPicBean bean) {
                if (bean != null){
                    if (bean.getStatus().equals("0000")){
                        view.ShangChuanHeadPicSuccess(bean.getMessage());
                    }else {
                        view.ShangChuanHeadPicError(bean.getMessage());
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                view.ShangChuanHeadPicError(e.toString());
            }
            @Override
            public void onComplete() {
            }
        });
    }
    //判断是否为空

    public String totNull(String old, String xin, String xin2) {
        if (TextUtils.isEmpty(old) || TextUtils.isEmpty(xin)|| TextUtils.isEmpty(xin2)) {
            return "不能有空";
        }
        if (old.length() < 6 || xin.length() < 6 || xin2.length() < 6 || old.length() > 16 || xin.length() > 16 || xin2.length() > 16) {
            return "密码长度6-16";
        }
        if (old.equals(xin)) {
            return "请勿使用相同密码";
        }
        if(!xin.equals(xin2)){
            return "两次输入新密码不同\n请检查后再次确定";
        }
        return "yes";
    }
    //加密
    public String Encryption(String pwd) {
        return EncryptUtil.encrypt(pwd);
    }
}
