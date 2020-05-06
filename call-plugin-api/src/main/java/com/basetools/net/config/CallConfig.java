package com.basetools.net.config;

import android.text.TextUtils;
import androidx.annotation.NonNull;

/**
 * 音视频通话初始化配置参数
 */
public class CallConfig {
    /**
     * 主域名
     */
    private String baseUrl;
    /**
     * 是否开启debug
     */
    private boolean debugEnable;
    /**
     * 用户Token
     */
    private String token;
    /**
     * 平台信息Json格式
     */
    private String platformJson;
    /**
     * 是否为海外项目集成
     */
    private boolean global;

    private CallConfig(String baseUrl, String token, String platformJson, boolean global, boolean debugEnable) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.platformJson = platformJson;
        this.global = global;
        this.debugEnable = debugEnable;
    }

    public String getBaseUrl() {
        if (!TextUtils.isEmpty(baseUrl)){
            // Retrofit$Builder java.lang.IllegalArgumentException: baseUrl must end in /
            if (!baseUrl.endsWith("/")){
                baseUrl += "/";
            }
        }
        return baseUrl;
    }

    public String getToken() {
        return token;
    }

    public boolean isDebugEnable() {
        return debugEnable;
    }

    public String getPlatformJson() {
        return platformJson;
    }

    public boolean isGlobal() {
        return global;
    }

    public static class Builder{
        /**
         * 主域名
         */
        private String baseUrl;
        /**
         * 是否开启debug
         */
        private boolean debugEnable;
        /**
         * 用户Token
         */
        private String token;
        /**
         * 平台信息Json格式
         */
        private String platformJson;
        /**
         * 是否为海外项目集成
         */
        private boolean global;

        public Builder(@NonNull String baseUrl, @NonNull String token, @NonNull String platformJson, boolean global) {
            this.baseUrl = baseUrl;
            this.token = token;
            this.platformJson = platformJson;
            this.global = global;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public Builder setDebugEnable(boolean debugEnable) {
            this.debugEnable = debugEnable;
            return this;
        }

        public Builder setPlatformJson(String platformJson) {
            this.platformJson = platformJson;
            return this;
        }

        public Builder setGlobal(boolean global) {
            this.global = global;
            return this;
        }

        public CallConfig build(){
            return new CallConfig(baseUrl, token, platformJson, global, debugEnable);
        }
    }
}
