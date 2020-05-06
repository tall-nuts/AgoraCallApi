package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

public class CreateChannelRequest{
    private int type;
    private String remoteUserId;
    private Platform platform;

    public CreateChannelRequest(int type, String remoteUserId) {
        this.type = type;
        this.remoteUserId = remoteUserId;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemoteUserId() {
        return remoteUserId;
    }

    public void setRemoteUserId(String remoteUserId) {
        this.remoteUserId = remoteUserId;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
