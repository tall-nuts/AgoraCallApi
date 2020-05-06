package com.basetools.task;

/**
 * 加入频道成功SDK内部需要执行的操作
 * @author gaopengfei on 2019/12/05.
 * @version v1.0.17
 */
public abstract class AbstractJoinChannelSuccessTask {
    /**
     * 加入频道成功，执行操作
     * @param token 加入频道需要的令牌
     */
    public abstract void run(String token);
}
