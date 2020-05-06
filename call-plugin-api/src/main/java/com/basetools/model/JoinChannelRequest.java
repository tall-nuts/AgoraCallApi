package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class JoinChannelRequest {
    private String roomId;
    /** 是否建立通话扣费：0否 1是 */
    private int loginFeeType;
    private Platform platform;

    public JoinChannelRequest(String roomId) {
        this.roomId = roomId;
        this.loginFeeType = 1;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public JoinChannelRequest(String roomId, int loginFeeType) {
        this.roomId = roomId;
        this.loginFeeType = loginFeeType;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "JoinChannelRequest{" +
                "roomId='" + roomId + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
