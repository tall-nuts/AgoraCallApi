package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.constant.RoomType;
import com.basetools.util.GsonUtils;

public class RandomRoomRequest {
    /** 11：随机视频 12：随机语音 */
    private int type;
    private Platform platform;

    public RandomRoomRequest(@RoomType int type) {
        this.type = type;
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "RandomRoomRequest{" +
                "type=" + type +
                ", platform=" + platform +
                '}';
    }
}
