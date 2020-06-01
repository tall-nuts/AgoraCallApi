package com.basetools.model;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private long userId;
    private String nickname;
    private int gender;
    private String avatar;
    private boolean isAnchor;
    private boolean isVip;

    public UserInfo(long userId, String nickname, int gender, String avatar, boolean isAnchor, boolean isVip) {
        this.userId = userId;
        this.nickname = nickname;
        this.gender = gender;
        this.avatar = avatar;
        this.isAnchor = isAnchor;
        this.isVip = isVip;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isAnchor() {
        return isAnchor;
    }

    public void setAnchor(boolean anchor) {
        isAnchor = anchor;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", avatar='" + avatar + '\'' +
                ", isAnchor=" + isAnchor +
                ", isVip=" + isVip +
                '}';
    }
}
