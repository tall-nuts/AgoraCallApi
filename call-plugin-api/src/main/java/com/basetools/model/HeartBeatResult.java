package com.basetools.model;

import java.io.Serializable;

/**
 * 心跳响应数据
 */
public class HeartBeatResult implements Serializable {

    private String msg;
    private int code;
    private int data;
    private Object ext;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    @Override
    public String toString() {
        return "HeartBeatResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", ext=" + ext +
                '}';
    }

    public static class Data {
        private int diamondNum;

        public int getDiamondNum() {
            return diamondNum;
        }

        public void setDiamondNum(int diamondNum) {
            this.diamondNum = diamondNum;
        }
    }
}
