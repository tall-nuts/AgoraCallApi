package com.basetools.model;

import java.io.Serializable;

/**
 * @author gaopengfei on 2019/12/16.
 */
public class JoinChannelResult implements Serializable {

    private int code;
    private String msg;
    private Token data;

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

    public Token getData() {
        return data;
    }

    public void setData(Token data) {
        this.data = data;
    }

    public static class Token {
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "token='" + token + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "JoinChannelResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
