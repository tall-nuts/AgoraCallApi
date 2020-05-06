package com.basetools.task;

/**
 * 心跳请求失败SDK内部需要执行的操作
 * @author gaopengfei on 2019/12/05.
 * @version v1.0.17
 */
public abstract class AbstractHeartbeatFailureTask {

    /**
     * 心跳接口请求失败执行操作
     */
    public abstract void run();
}