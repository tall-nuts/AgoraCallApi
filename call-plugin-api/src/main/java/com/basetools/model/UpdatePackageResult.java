package com.basetools.model;

/**
 * 1v1音视频插件检测更新
 */
public class UpdatePackageResult {
    private String msg;
    private int code;
    private CallPluginInfo data;

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

    public CallPluginInfo getData() {
        return data;
    }

    public void setData(CallPluginInfo data) {
        this.data = data;
    }

    public static class CallPluginInfo {
        /**
         * 声网1v1插件下载地址
         */
        private String oneToOneUrl;
        /**
         * 声网1v1插件版本
         */
        private int oneToOneVer;

        public String getOneToOneUrl() {
            return oneToOneUrl;
        }

        public void setOneToOneUrl(String oneToOneUrl) {
            this.oneToOneUrl = oneToOneUrl;
        }

        public int getOneToOneVer() {
            return oneToOneVer;
        }

        public void setOneToOneVer(int oneToOneVer) {
            this.oneToOneVer = oneToOneVer;
        }

        @Override
        public String toString() {
            return "CallPluginInfo{" +
                    "oneToOneUrl='" + oneToOneUrl + '\'' +
                    ", oneToOneVer=" + oneToOneVer +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UpdatePackageResult{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
