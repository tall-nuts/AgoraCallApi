package com.basetools.model;

import java.io.Serializable;

public class RandomRoomResult implements Serializable {

    private int code;
    private String msg;
    private RandomRoomData data;

    public RandomRoomResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RandomRoomData getData() {
        return data;
    }

    public void setData(RandomRoomData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RandomRoomResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
