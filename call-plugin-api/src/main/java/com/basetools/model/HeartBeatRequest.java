package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class HeartBeatRequest {
    private String roomId;
    private String chatId;
    /** 是否建立通话扣费：0否 1是 */
    private int loginFeeType;
    private Platform platform;

    public HeartBeatRequest(String roomId, String chatId) {
        this.roomId = roomId;
        this.chatId = chatId;
        this.loginFeeType = 1;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public HeartBeatRequest(String roomId, String chatId, int loginFeeType) {
        this.roomId = roomId;
        this.chatId = chatId;
        this.loginFeeType = loginFeeType;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getLoginFeeType() {
        return loginFeeType;
    }

    public void setLoginFeeType(int loginFeeType) {
        this.loginFeeType = loginFeeType;
    }

    @Override
    public String toString() {
        return "HeartBeatRequest{" +
                "roomId='" + roomId + '\'' +
                ", chatId='" + chatId + '\'' +
                ", loginFeeType=" + loginFeeType +
                ", platform=" + platform +
                '}';
    }
}
