package com.beanu.l2_shareutil.login;

import java.util.Map;

/**
 * Created by shaohui on 2016/12/1.
 */

public class BaseUser {

    private String openId;
    private String nickname;
    private int sex;
    private String headImageUrl;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public static BaseUser getBaseUser(Map<String, String> resultMap) {
        BaseUser baseUser = new BaseUser();
        baseUser.setOpenId(resultMap.get("uid"));
        baseUser.setNickname(resultMap.get("name"));
        baseUser.setSex("男".equals(resultMap.get("gender")) ? 0 : 1);
        baseUser.setHeadImageUrl(resultMap.get("iconurl"));
        return baseUser;
    }

}
