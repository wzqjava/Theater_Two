package com.bw.movie.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * date:2019/1/5
 * author:赵豪轩(xuan)
 * function:
 */
@Entity
public class UserBean {

    @Property(nameInDb = "SESSIONID")
    private String sessionId;
    @Property(nameInDb = "USERID")
    private String userId;
    @Property(nameInDb = "BIRTHDAY")
    private String birthday;
    @Property(nameInDb = "ID")
    private String id;
    @Property(nameInDb = "LASTLOGINTIME")
    private String lastLoginTime;
    @Property(nameInDb = "NICKNAME")
    private String nickName;
    @Property(nameInDb = "PHONE")
    private String phone;
    @Property(nameInDb = "SEX")
    private String sex;
    @Property(nameInDb = "HEADPIC")
    private String headPic;
    @Generated(hash = 1430982445)
    public UserBean(String sessionId, String userId, String birthday, String id,
            String lastLoginTime, String nickName, String phone, String sex,
            String headPic) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.birthday = birthday;
        this.id = id;
        this.lastLoginTime = lastLoginTime;
        this.nickName = nickName;
        this.phone = phone;
        this.sex = sex;
        this.headPic = headPic;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }


    

}
