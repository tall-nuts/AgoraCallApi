package com.basetools.task;

import com.basetools.constant.RoomType;

public abstract class AbstractRandomMatchSuccessTask {
    /**
     * 随机匹配接口执行成功，执行操作
     * @param roomType 房间类型 {@link RoomType#RANDOM_VIDEO_CALL} {@link RoomType#RANDOM_VOICE_CALL}
     * @param roomId 房间ID
     * @param token 加入频道需要的令牌
     */
    public abstract void run(@RoomType int roomType, long roomId, String token);
}