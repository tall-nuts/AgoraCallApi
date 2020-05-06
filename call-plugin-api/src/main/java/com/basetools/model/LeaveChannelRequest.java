package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class LeaveChannelRequest {
    private String roomId;
    private Platform platform;

    public LeaveChannelRequest(String roomId) {
        this.roomId = roomId;
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
        return "LeaveChannelRequest{" +
                "roomId='" + roomId + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
