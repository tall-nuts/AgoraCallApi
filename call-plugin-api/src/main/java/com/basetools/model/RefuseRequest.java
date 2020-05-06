package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class RefuseRequest {
    private long userId;
    private int refuseType;
    private Platform platform;

    public RefuseRequest(long userId, int refuseType) {
        this.userId = userId;
        this.refuseType = refuseType;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRefuseType() {
        return refuseType;
    }

    public void setRefuseType(int refuseType) {
        this.refuseType = refuseType;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "RefuseRequest{" +
                "userId=" + userId +
                ", refuseType=" + refuseType +
                ", platform='" + platform + '\'' +
                '}';
    }
}
