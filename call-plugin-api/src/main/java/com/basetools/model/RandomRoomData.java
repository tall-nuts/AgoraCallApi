package com.basetools.model;

import java.io.Serializable;

public class RandomRoomData implements Serializable {
    /** 频道ID */
    private long roomId;
    /** 声网加入频道所需token */
    private String token;

    public RandomRoomData() {
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "RandomRoomData{" +
                "roomId=" + roomId +
                ", token='" + token + '\'' +
                '}';
    }
}
