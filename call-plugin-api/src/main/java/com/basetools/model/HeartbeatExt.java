package com.basetools.model;

/**
 * 心跳接口返回数据扩展字段
 */
public class HeartbeatExt {
    /**
     * 远端用户地区
     */
    private String location;
    /**
     * 远端用户钻石余额
     */
    private String accountDia;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccountDia() {
        return accountDia;
    }

    public void setAccountDia(String accountDia) {
        this.accountDia = accountDia;
    }

    @Override
    public String toString() {
        return "Ext{" +
                "location='" + location + '\'' +
                ", accountDia='" + accountDia + '\'' +
                '}';
    }
}
