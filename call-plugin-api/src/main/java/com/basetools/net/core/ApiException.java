package com.basetools.net.core;

public class ApiException extends RuntimeException {

    public static final int CODE_FAILED = 0;
    public static final int CODE_SUCCEED = 1;
    public static final int CODE_EMPTY = -1;
    public static final int CODE_MSG_INTERREPT = 1300;
    // token失效
    public static final int BUSINESS_CODE_TOKEN_INVALID = 1000;
    // 用户不存在
    public static final int BUSINESS_CODE_USER_NOT_EXIST = 1001;
    // platform对象转换失败
    public static final int BUSINESS_CODE_OBJECT_CONVERSION_FAILED = 1002;
    // 参数不正确
    public static final int BUSINESS_CODE_PARAMETER_INCORRECT = 1003;
    // 服务器错误
    public static final int BUSINESS_CODE_SERVER_ERROR = 1004;
    // 用户类型错误
    public static final int BUSINESS_CODE_USER_TYPE_ERROR  = 1005;
    // 重复请求
    public static final int BUSINESS_CODE_REPEAT_REQUEST = 1006;
    // 用户被封号
    public static final int BUSINESS_CODE_ACCOUNT_DISABLED = 1007;
    // 验证码错误
    public static final int BUSINESS_CODE_VERIFY_CODE_ERROR = 1008;
    // 余额不足
    public static final int BUSINESS_CODE_INSUFFICIENT_BALANCE_ERROR = 1100;
    // 房间不存在或房间已关闭
    public static final int BUSINESS_CODE_ROOM_NOT_EXIST_ERROR = 1101;
    // 已是好友
    public static final int BUSINESS_CODE_FRIENDS_ALREADY_ERROR = 1207;
    // 不是好友
    public static final int BUSINESS_CODE_NOT_FRIENDS_ERROR = 1303;
    // 对方已拉黑
    public static final int BUSINESS_CODE_BLACK_LIST_ERROR = 1103;
    // 需要VIP权限
    public static final int BUSINESS_CODE_VIP_ERROR = 1304;

    private int code;
    private String msg;

    public ApiException(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
