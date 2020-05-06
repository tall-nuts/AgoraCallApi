package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class GiveGiftMsgRequest {
    private long userId;
    private String giftId;
    private int count;
    private int type = 1;
    private int isNewApp = 1;
    private Platform platform;

    public GiveGiftMsgRequest(long userId, String giftId, int count) {
        this.userId = userId;
        this.giftId = giftId;
        this.count = count;
        this.type = 1;
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
                ", type=" + type +
                ", isNewApp=" + isNewApp +
                ", platform='" + platform + '\'' +
                '}';
    }
}
