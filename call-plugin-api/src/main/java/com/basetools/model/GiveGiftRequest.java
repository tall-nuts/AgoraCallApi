package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class GiveGiftRequest {
    private long userId;
    private String giftId;
    private int count;
    private long roomId;
    private int type = 2;
    private int isNewApp = 1;
    private Platform platform;

    public GiveGiftRequest(long userId, String giftId, int count, long roomId) {
        this.userId = userId;
        this.giftId = giftId;
        this.count = count;
        this.roomId = roomId;
        this.type = 2;
        this.isNewApp = 1;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsNewApp() {
        return isNewApp;
    }

    public void setIsNewApp(int isNewApp) {
        this.isNewApp = isNewApp;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "GiveGiftRequest{" +
                "userId=" + userId +
                ", giftId='" + giftId + '\'' +
                ", count=" + count +
                ", roomId=" + roomId +
                ", type=" + type +
                ", isNewApp=" + isNewApp +
                ", platform='" + platform + '\'' +
                '}';
    }
}
