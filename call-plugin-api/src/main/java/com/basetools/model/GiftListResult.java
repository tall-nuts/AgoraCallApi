package com.basetools.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author gaopengfei on 2019/12/17.
 */
public class GiftListResult implements Serializable {

    private String msg;
    private int code;
    private GiftData data;

    public static class GiftData implements Serializable  {

        private long lastTime;
        private ArrayList<Gift> gifts;

        public long getLastTime() {
            return lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public ArrayList<Gift> getGifts() {
            return gifts;
        }

        public void setGifts(ArrayList<Gift> gifts) {
            this.gifts = gifts;
        }

        @Override
        public String toString() {
            return "GiftListResult{" +
                    "lastTime=" + lastTime +
                    ", gifts=" + gifts +
                    '}';
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public GiftData getData() {
        return data;
    }

    public void setData(GiftData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GiftListResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
