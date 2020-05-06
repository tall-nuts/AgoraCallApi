package com.basetools.task;

/**
 * 创建频道成功SDK内部需要执行的操作
 * @author gaopengfei on 2019/12/05.
 * @version v1.0.17
 */
public abstract class AbstractCreateChannelSuccessTask {

    /**
     * 创建频道成功，执行回调
     */
    public abstract void run(String channelId);
}
