package com.basetools.task;

/**
 * 心跳请求成功SDK内部需要执行的操作
 * @author gaopengfei on 2019/12/05.
 */
public abstract class AbstractHeartbeatSuccessTask {

    /**
     * 心跳请求成功
     * @param balance 当前用户余额
     */
    public abstract void run(long balance);

    /**
     * 心跳请求成功
     * @param balance 当前用户余额
     * @param ext 扩展数据
     */
    public abstract void run(long balance, Object ext);
}
