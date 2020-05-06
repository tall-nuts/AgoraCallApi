package com.basetools.api;

import android.content.Context;
import com.basetools.constant.RoomType;
import com.basetools.task.AbstractCreateChannelFailureTask;
import com.basetools.task.AbstractCreateChannelSuccessTask;
import com.basetools.task.AbstractHeartbeatFailureTask;
import com.basetools.task.AbstractHeartbeatSuccessTask;
import com.basetools.task.AbstractJoinChannelFailureTask;
import com.basetools.task.AbstractJoinChannelSuccessTask;
import com.basetools.task.IBaseTask;
import java.io.Serializable;

/**
 * 集成方法：
 * </br>
 * 1. 宿主按照RePlugin集成文档正常操作。</br>
 * 2. 国内应用在宿主中创建固定包名 "com.juzhionline.callplugin.callapi" 并在该包名下创建固定类 CallApiImpl.java实现ICallApi接口，实现对应方法中业务逻辑。</br>
 * 3. 海外应用在宿主中创建固定包名 "io.agora.opensource.callapi" 并在该包名下创建固定类 CallApiImpl.java实现ICallApi接口，实现对应方法中业务逻辑。</br>
 * </br>
 * 说明：</br>
 * 该module所编译生成的jar包同时会在callPlugin插件中引入依赖，插件中会通过反射调用CallApiImpl.java类进行使用。</br>
 *
 * @author gaopengfei on 2019/12/05.
 * @version v1.0.19
 */
public interface ICallApi {
    /**
     * 加入频道
     * @param channelId 频道ID
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void joinChannel(String channelId, AbstractJoinChannelSuccessTask okTask, AbstractJoinChannelFailureTask errorTask);

    /**
     * 离开频道
     * @param channelId 频道ID
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void leaveChannel(String channelId, IBaseTask okTask, IBaseTask errorTask);

    /**
     * 心跳
     * @param channelId 频道ID
     * @param chatId 消息ID
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void heartBeat(String channelId, long chatId, AbstractHeartbeatSuccessTask okTask, AbstractHeartbeatFailureTask errorTask);

    /**
     * 拒绝通话
     * @param remoteUid 对方ID
     * @param refuseType 拒绝类型：0 挂断 1 忙碌中 2 呼叫方取消
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void refuseCall(long remoteUid, int refuseType, IBaseTask okTask, IBaseTask errorTask);

    /**
     * 创建频道
     * @param roomType 类型：8：视频  9：语音
     * @param remoteUid 对方ID
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void createChannel(@RoomType int roomType, long remoteUid, AbstractCreateChannelSuccessTask okTask, AbstractCreateChannelFailureTask errorTask);

    /**
     * 送礼物
     * @param context Contenxt
     * @param userId 收礼人ID
     * @param channelId 频道ID
     * @param giftId 礼物ID
     * @param count 礼物数量
     */
    @Deprecated
    void giveGift(Context context, long userId, long channelId, String giftId, int count);

    /**
     * 埋点统计
     * @param eventType 事件类型
     */
    @Deprecated
    void logMonitoring(String eventType);

    /**
     * 发私信
     * @param remoteUid 收信人ID
     * @param remoteNickname 收信人昵称
     * @param remoteAvatar 收信人头像
     * @param message 消息内容
     */
    @Deprecated
    void sendPrivateMessage(String remoteUid, String remoteNickname, String remoteAvatar, String message);

    /**
     * 加入频道
     * @param channelId 频道ID
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void joinChannel(String channelId, Serializable ext, AbstractJoinChannelSuccessTask okTask, AbstractJoinChannelFailureTask errorTask);

    /**
     * 加入频道
     * @param channelId 频道ID
     * @param loginFeeType 是否已建立通话(注：joinChannel、heartBeat都传) 0 否 1 是
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    void joinChannel(String channelId, int loginFeeType, Serializable ext, AbstractJoinChannelSuccessTask okTask, AbstractJoinChannelFailureTask errorTask);

    /**
     * 离开频道
     * @param channelId 频道ID
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    void leaveChannel(String channelId, Serializable ext, IBaseTask okTask, IBaseTask errorTask);

    /**
     * 心跳
     * @param channelId 频道ID
     * @param chatId 消息ID
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    @Deprecated
    void heartBeat(String channelId, long chatId, Serializable ext, AbstractHeartbeatSuccessTask okTask, AbstractHeartbeatFailureTask errorTask);

    /**
     * 心跳
     * @param channelId 频道ID
     * @param chatId 消息ID
     * @param loginFeeType 是否已建立通话(注：joinChannel、heartBeat都传) 0 否 1 是
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    void heartBeat(String channelId, long chatId, int loginFeeType, Serializable ext, AbstractHeartbeatSuccessTask okTask, AbstractHeartbeatFailureTask errorTask);

    /**
     * 拒绝通话
     * @param remoteUid 对方ID
     * @param refuseType 拒绝类型：0 挂断 1 忙碌中 2 呼叫方取消
     * @param channelId 频道ID
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    void refuseCall(String channelId, long remoteUid, int refuseType, Serializable ext, IBaseTask okTask, IBaseTask errorTask);

    /**
     * 创建频道
     * @param roomType 类型：8：视频  9：语音
     * @param remoteUid 对方ID
     * @param ext 扩展参数
     * @param okTask 接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    void createChannel(@RoomType int roomType, long remoteUid, Serializable ext, AbstractCreateChannelSuccessTask okTask, AbstractCreateChannelFailureTask errorTask);

    /**
     * 送礼物
     * @param context Contenxt
     * @param userId 收礼人ID
     * @param channelId 频道ID
     * @param giftId 礼物ID
     * @param count 礼物数量
     * @param ext 扩展参数
     */
    void giveGift(Context context, long userId, long channelId, String giftId, int count, Serializable ext);

    /**
     * 埋点统计
     * @param eventType 事件类型
     * @param ext 扩展参数
     */
    void logMonitoring(String eventType, Serializable ext);

    /**
     * 发私信
     * @param remoteUid 收信人ID
     * @param remoteNickname 收信人昵称
     * @param remoteAvatar 收信人头像
     * @param message 消息内容
     * @param ext 扩展参数
     */
    void sendPrivateMessage(String remoteUid, String remoteNickname, String remoteAvatar, String message, Serializable ext);
}
