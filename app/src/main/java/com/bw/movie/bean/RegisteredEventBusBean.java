package com.bw.movie.bean;

/**
 * date:2019/1/5
 * author:李壮(大壮)
 * function:注册回显账号密码
 */
public class RegisteredEventBusBean {
    public String phone;
    public String pwd;

    public RegisteredEventBusBean(String phone, String pwd) {
        this.phone = phone;
        this.pwd = pwd;
    }

    public RegisteredEventBusBean() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
