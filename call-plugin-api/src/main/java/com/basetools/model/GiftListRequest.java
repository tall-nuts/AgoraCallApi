package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class GiftListRequest {
    private long lastTime;
    private Platform platform;
    private int isNewApp;

    public GiftListRequest(long lastTime) {
        this.lastTime = lastTime;
        this.isNewApp = 1;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public int getIsNewApp() {
        return isNewApp;
    }

    public void setIsNewApp(int isNewApp) {
        this.isNewApp = isNewApp;
    }

    @Override
    public String toString() {
        return "GiftListRequest{" +
                "lastTime=" + lastTime +
                ", platform='" + platform + '\'' +
                ", isNewApp=" + isNewApp +
                '}';
    }
}
