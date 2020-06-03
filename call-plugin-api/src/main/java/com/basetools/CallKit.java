package com.basetools;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.os.Process;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.basetools.api.ICallService;
import com.basetools.constant.InviteType;
import com.basetools.constant.RoomType;
import com.basetools.listener.CheckCallPluginVersionListener;
import com.basetools.model.BaseResult;
import com.basetools.model.CreateChannelRequest;
import com.basetools.model.CreateChannelResult;
import com.basetools.model.GiveGiftRequest;
import com.basetools.model.HeartBeatRequest;
import com.basetools.model.HeartBeatResult;
import com.basetools.model.HeartBeatResult2;
import com.basetools.model.JoinChannelRequest;
import com.basetools.model.JoinChannelResult;
import com.basetools.model.LeaveChannelRequest;
import com.basetools.model.RandomRoomRequest;
import com.basetools.model.RandomRoomResult;
import com.basetools.model.RefuseRequest;
import com.basetools.model.UpdatePackageRequest;
import com.basetools.model.UpdatePackageResult;
import com.basetools.net.config.CallConfig;
import com.basetools.net.core.ApiException;
import com.basetools.net.core.ApiObserver;
import com.basetools.net.repository.CallRepository;
import com.basetools.task.AbstractCreateChannelFailureTask;
import com.basetools.task.AbstractCreateChannelSuccessTask;
import com.basetools.task.AbstractHeartbeatFailureTask;
import com.basetools.task.AbstractHeartbeatSuccessTask;
import com.basetools.task.AbstractJoinChannelFailureTask;
import com.basetools.task.AbstractJoinChannelSuccessTask;
import com.basetools.task.AbstractRandomMatchFailureTask;
import com.basetools.task.AbstractRandomMatchSuccessTask;
import com.basetools.task.IBaseTask;
import com.basetools.util.Timber;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 1V1通话CallActivity启动工具
 */
public class CallKit {

    /**
     * 国内音视频通话模块包名： com.juzhionline.callplugin
     */
    private static final String DEFAULT_PACKAGE_NAME = "Y29tLmp1emhpb25saW5lLmNhbGxwbHVnaW4=";
    /**
     * 海外音视频通话模块包名：io.agora.opensource
     */
    private static final String GLOBAL_PACKAGE_NAME = "aW8uYWdvcmEub3BlbnNvdXJjZQ==";
    /**
     * 应用实例
     */
    private static Application mApp;
    /**
     * 初始化配置参数
     */
    private static CallConfig mCallConfig;
    /**
     * 公开业务api接口
     */
    private static ICallService mCallService;

    private static class SingletonHolder {
        private static final CallKit sInstance = new CallKit();
    }

    public static CallKit getInstance() {
        if (mCallConfig == null) {
            throw new RuntimeException("Please initialize CallKit first!!!");
        }
        return CallKit.SingletonHolder.sInstance;
    }

    /**
     * module引用 初始化
     *
     * @param application 应用实例
     * @param config      配置
     */
    public static void initForModule(@NonNull Application application, @NonNull CallConfig config) {
        Timber.setDebugEnable(config.isDebugEnable());
        mApp = application;
        mCallConfig = config;
        boolean isMainProcess = application.getApplicationContext().getPackageName().equals(getCurrentProcessName(application));
        // 只有在主进程时初始化相应数据
        if (isMainProcess) {
            try {
                Class<?> clazz = Class.forName(getAppPath(config.isGlobal()));
                if (clazz != null) {
                    Object obj = clazz.newInstance();
                    if (obj instanceof IComponentApplication) {
                        ((IComponentApplication) obj).init(application);
                        Timber.d("initForModule success.");
                    } else {
                        Timber.e("");
                    }
                } else {
                    Timber.e("App is null, initForModule failure!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Timber.e("This process is not main process, init failure!");
        }
    }

    /**
     * RePlugin 引用 初始化
     *
     * @param application 应用实例
     * @param config      配置
     */
    public static void initForRePlugin(@NonNull Application application, @NonNull CallConfig config) {
        Timber.setDebugEnable(config.isDebugEnable());
        mApp = application;
        mCallConfig = config;
        Timber.d("initForRePlugin success.");
    }

    public Application getApplication() {
        return mApp;
    }

    public Context getContext() {
        return mApp.getApplicationContext();
    }

    public boolean isDebugEnable() {
        return mCallConfig != null && mCallConfig.isDebugEnable();
    }

    public String getBaseUrl() {
        return mCallConfig != null ? mCallConfig.getBaseUrl() : "";
    }

    public String getToken() {
        return mCallConfig != null ? mCallConfig.getToken() : "";
    }

    public String getPlatformJson() {
        return mCallConfig != null ? mCallConfig.getPlatformJson() : "";
    }

    public ICallService getICallService() {
        if (mCallService == null) {
            mCallService = reflectICallServiceNewInstance();
        }
        return mCallService;
    }

    /**
     * 反射创建ICallService对象
     *
     * @return ICallService
     */
    private ICallService reflectICallServiceNewInstance() {
        if (mCallConfig == null) {
            throw new RuntimeException("Please initialize CallKit first!!!");
        }
        try {
            Class<?> callServiceImplClass = Class.forName(getCallPluginPackageName(mCallConfig.isGlobal()) + ".callapi.CallServiceImpl");
            if (callServiceImplClass != null) {
                Object callServiceImpl = callServiceImplClass.newInstance();
                return (ICallService) callServiceImpl;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Timber.e("Reflect ICallServiceNewInstance failure, !!!");
        return null;
    }

    /**
     * 启动内置通话UI
     *
     * @param context        上下文对象
     * @param channelId      频道ID
     * @param callType       通话类型 8:视频 9:语音 {@link RoomType}
     * @param remoteUid      对方ID
     * @param remoteNickname 对方昵称
     * @param remoteAvatar   对方头像
     * @param inviteCallType 邀请类型：0：发起邀请 1：收到邀请 2：收到系统策略邀请
     * @param ext            扩展参数，会在ICallApi的每个方法中返回
     */
    public static Intent createCallIntentForGlobalModule(Context context, long channelId, @RoomType int callType, long remoteUid,
                                                         @NonNull String remoteNickname, @NonNull String remoteAvatar, @InviteType int inviteCallType, Serializable ext) {
        Intent intent = new Intent();
        intent.putExtra("roomId", channelId);
        intent.putExtra("roomType", callType);
        intent.putExtra("remoteUid", remoteUid);
        intent.putExtra("remoteNickname", remoteNickname);
        intent.putExtra("remoteAvatar", remoteAvatar);
        intent.putExtra("inviteCallType", inviteCallType);
        intent.putExtra("ext", ext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(context.getPackageName(), getCallActivityPath(true));
        return intent;
    }

    /**
     * 启动内置通话UI
     *
     * @param context        上下文对象
     * @param channelId      频道ID
     * @param callType       通话类型 8:视频 9:语音 {@link RoomType}
     * @param remoteUid      对方ID
     * @param remoteNickname 对方昵称
     * @param remoteAvatar   对方头像
     * @param inviteCallType 邀请类型：0：发起邀请 1：收到邀请 2：收到系统策略邀请
     * @param ext            扩展参数，会在ICallApi的每个方法中返回
     */
    public static Intent createCallIntentForModule(Context context, long channelId, @RoomType int callType, long remoteUid,
                                                   @NonNull String remoteNickname, @NonNull String remoteAvatar, @InviteType int inviteCallType, Serializable ext) {
        Intent intent = new Intent();
        intent.putExtra("roomId", channelId);
        intent.putExtra("roomType", callType);
        intent.putExtra("remoteUid", remoteUid);
        intent.putExtra("remoteNickname", remoteNickname);
        intent.putExtra("remoteAvatar", remoteAvatar);
        intent.putExtra("inviteCallType", inviteCallType);
        intent.putExtra("ext", ext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(context.getPackageName(), getCallActivityPath(false));
        return intent;
    }

    /**
     * 启动内置通话UI
     *
     * @param channelId      频道ID
     * @param callType       通话类型 8:视频 9:语音 {@link RoomType}
     * @param remoteUid      对方ID
     * @param remoteNickname 对方昵称
     * @param remoteAvatar   对方头像
     * @param inviteCallType 邀请类型：0：发起邀请 1：收到邀请 2：收到系统策略邀请
     * @param ext            扩展参数，会在ICallApi的每个方法中返回
     */
    public static Intent createCallIntentForPlugin(long channelId, @RoomType int callType, long remoteUid,
                                                   @NonNull String remoteNickname, @NonNull String remoteAvatar, @InviteType int inviteCallType, Serializable ext) {
        Intent intent = new Intent();
        intent.putExtra("roomId", channelId);
        intent.putExtra("roomType", callType);
        intent.putExtra("remoteUid", remoteUid);
        intent.putExtra("remoteNickname", remoteNickname);
        intent.putExtra("remoteAvatar", remoteAvatar);
        intent.putExtra("inviteCallType", inviteCallType);
        intent.putExtra("ext", ext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName("callPlugin", getCallActivityPath(false)));
        return intent;
    }

    /**
     * 创建随机匹配
     *
     * @param roomType 房间类型 11 || 12
     * @param ext      扩展参数，会在ICallApi方法中返回
     */
    public static Intent createRandomMatchIntentForModule(Context context, @RoomType int roomType, Serializable ext) {
        Intent intent = new Intent();
        intent.putExtra("roomType", roomType);
        intent.putExtra("ext", ext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(context.getPackageName(), getCallActivityPath(false));
        return intent;
    }

    /**
     * 创建随机匹配
     *
     * @param roomType 房间类型 11 || 12
     * @param ext      扩展参数，会在ICallApi方法中返回
     */
    public static Intent createRandomMatchIntentForGlobalModule(Context context, @RoomType int roomType, Serializable ext) {
        Intent intent = new Intent();
        intent.putExtra("roomType", roomType);
        intent.putExtra("ext", ext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(context.getPackageName(), getCallActivityPath(true));
        return intent;
    }

    /**
     * 创建随机匹配
     *
     * @param roomType 房间类型 11 || 12
     * @param ext      扩展参数，会在ICallApi方法中返回
     */
    public static Intent createRandomMatchIntentForPlugin(@RoomType int roomType, Serializable ext) {
        Intent intent = new Intent();
        intent.putExtra("roomType", roomType);
        intent.putExtra("ext", ext);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName("callPlugin", getCallActivityPath(false)));
        return intent;
    }

    /**
     * 取消匹配（发送广播来关闭）
     *
     * @param isGlobal 是否是海外项目集成
     */
    public static void cancelRandomMatch(Context context, boolean isGlobal) {
        Intent intent = new Intent();
        intent.setAction(isGlobal
                ? "io.agora.opensource.call.action.PUSH"
                : "com.juzhionline.call.action.PUSH");
        intent.putExtra("type", 20);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    /**
     * 获取音视频插件或Module依赖的包名
     *
     * @param global 是否为海外集成
     * @return 包名
     */
    public static String getCallPluginPackageName(boolean global) {
        try {
            return new String(Base64.decode(global
                    ? GLOBAL_PACKAGE_NAME.getBytes("UTF-8")
                    : DEFAULT_PACKAGE_NAME.getBytes("UTF-8"), Base64.NO_WRAP));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Timber.e("getCallPluginPackageName exception：" + e.getMessage());
            return "";
        }
    }

    /**
     * 获取CallActivity路径
     *
     * @param global 是否是海外项目
     * @return
     */
    public static String getCallActivityPath(boolean global) {
        return getCallPluginPackageName(global) + ".ui.CallActivity";
    }

    /**
     * 获取App.java 全路径
     *
     * @param global 是否是海外项目
     * @return App.java 全路径
     */
    public static String getAppPath(boolean global) {
        try {
            return new String(Base64.decode(global
                    ? GLOBAL_PACKAGE_NAME.getBytes("UTF-8")
                    : DEFAULT_PACKAGE_NAME.getBytes("UTF-8"), Base64.NO_WRAP)) + ".App";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Timber.e("getAppPath exception：" + e.getMessage());
            return "";
        }
    }

    /**
     * 获取当前进程名
     *
     * @param context 上下文对象
     * @return 当前进程名称
     */
    private static String getCurrentProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> info = am.getRunningAppProcesses();
        if (info == null || info.size() == 0) {
            return null;
        }
        int pid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo aInfo : info) {
            if (aInfo.pid == pid) {
                if (aInfo.processName != null) {
                    Timber.d("getCurrentProcessName >>> " + aInfo.processName);
                    return aInfo.processName;
                }
            }
        }
        Timber.d("getCurrentProcessName >>> null");
        return "";
    }

    /* ******************************************************** ICallApi ********************************************************** */

    /**
     * 检查通话插件版本信息
     *
     * @param listener 回调监听
     */
    public void checkCallPluginVersion(CheckCallPluginVersionListener listener) {
        Timber.d("checkCallPluginVersion >>>");
        CallRepository.getInstance().checkCallPluginVersion(new UpdatePackageRequest(), new ApiObserver<UpdatePackageResult>() {

            @Override
            public void onNext(UpdatePackageResult updatePackageResult) {
                if (updatePackageResult != null) {
                    UpdatePackageResult.CallPluginInfo callPluginInfo = updatePackageResult.getData();
                    if (callPluginInfo != null && listener != null) {
                        listener.onFetchCallPluginVersionSuccess(callPluginInfo.getOneToOneUrl(), callPluginInfo.getOneToOneVer());
                    }
                }
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                if (listener != null) {
                    int code = -1;
                    if (e instanceof ApiException) {
                        code = ((ApiException) e).getCode();
                    }
                    listener.onFetchCallPluginVersionFailure(code, msg);
                }
            }
        });
    }

    /**
     * 随机匹配
     *
     * @param roomType  随机视频|随机语音匹配
     * @param okTask    接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    public void randomMatch(@RoomType int roomType, Serializable ext, AbstractRandomMatchSuccessTask okTask, AbstractRandomMatchFailureTask errorTask) {
        CallRepository.getInstance().randomRoom(new RandomRoomRequest(roomType), new ApiObserver<RandomRoomResult>() {
            @Override
            public void onNext(RandomRoomResult randomRoomResult) {
                if (okTask != null) {
                    okTask.run(roomType, randomRoomResult.getData().getRoomId(), randomRoomResult.getData().getToken());
                }
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                Toast.makeText(getContext(), msg + "", Toast.LENGTH_SHORT).show();
                boolean runErrorTask = isNotInterceptException(e);
                if (runErrorTask && errorTask != null) {
                    errorTask.run();
                }
            }
        });
    }

    /**
     * 加入频道
     *
     * @param channelId    频道ID
     * @param loginFeeType 是否已建立通话(注：joinChannel、heartBeat都传) 0 否 1 是
     * @param ext          扩展参数
     * @param okTask       接口执行成功后需要执行的任务
     * @param errorTask    接口执行失败后执行的任务
     */
    public void joinChannel(String channelId, int loginFeeType, Serializable ext, AbstractJoinChannelSuccessTask okTask, AbstractJoinChannelFailureTask errorTask) {
        Timber.d("joinChannel >>> channelId:" + channelId + " | ext:" + ext);
        CallRepository.getInstance().joinChannel(new JoinChannelRequest(channelId, loginFeeType), new ApiObserver<JoinChannelResult>() {

            @Override
            public void onNext(JoinChannelResult joinChannelResult) {
                if (okTask != null) {
                    String token = "";
                    if (joinChannelResult.getData() != null) {
                        token = joinChannelResult.getData().getToken();
                    }
                    okTask.run(token);
                    getICallService().onCallConnected(channelId, ext);
                }
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                Toast.makeText(getContext(), msg + "", Toast.LENGTH_SHORT).show();
                boolean runErrorTask = isNotInterceptException(e);
                if (runErrorTask && errorTask != null) {
                    errorTask.run();
                }
            }
        });
    }

    /**
     * 离开频道
     *
     * @param channelId 频道ID
     * @param ext       扩展参数
     * @param okTask    接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    public void leaveChannel(String channelId, Serializable ext, IBaseTask okTask, IBaseTask errorTask) {
        Timber.d("leaveChannel >>> channelId:" + channelId + " | ext:" + ext);
        CallRepository.getInstance().leaveChannel(new LeaveChannelRequest(channelId), new ApiObserver<BaseResult>() {
            @Override
            public void onNext(BaseResult baseResult) {
                super.onNext(baseResult);
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                if (errorTask != null) {
                    errorTask.run();
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 心跳
     *
     * @param channelId    频道ID
     * @param chatId       消息ID
     * @param loginFeeType 是否已建立通话(注：joinChannel、heartBeat都传) 0 否 1 是
     * @param ext          扩展参数
     * @param okTask       接口执行成功后需要执行的任务
     * @param errorTask    接口执行失败后执行的任务
     */
    public void heartBeat(String channelId, long chatId, int loginFeeType, Serializable ext, AbstractHeartbeatSuccessTask okTask, AbstractHeartbeatFailureTask errorTask) {
        Timber.d("heartBeat >>> channelId:" + channelId + " | chatId:" + chatId + " | loginFeeType:" + loginFeeType + " | ext:" + ext);
        if (mCallConfig != null && mCallConfig.isFixHeartbeatApi()) {
            CallRepository.getInstance().heartbeatV2(new HeartBeatRequest(channelId, String.valueOf(chatId), loginFeeType), new ApiObserver<HeartBeatResult2>() {
                @Override
                public void onNext(HeartBeatResult2 heartBeatResult) {
                    if (mCallService != null) {
                        mCallService.updateDiamondBalance(heartBeatResult.getData().getDiamondNum());
                    }
                    if (okTask != null) {
                        okTask.run(heartBeatResult.getData().getDiamondNum(), heartBeatResult.getExt());
                    }
                }

                @Override
                protected void onErrorResolved(Throwable e, String msg) {
                    Toast.makeText(getContext(), msg + "", Toast.LENGTH_SHORT).show();
                    if (errorTask != null) {
                        errorTask.run();
                    }
                }
            });
        } else {
            CallRepository.getInstance().heartbeat(new HeartBeatRequest(channelId, String.valueOf(chatId), loginFeeType), new ApiObserver<HeartBeatResult>() {
                @Override
                public void onNext(HeartBeatResult heartBeatResult) {
                    if (mCallService != null) {
                        mCallService.updateDiamondBalance(heartBeatResult.getData());
                    }
                    if (okTask != null) {
                        okTask.run(heartBeatResult.getData(), heartBeatResult.getExt());
                    }
                }

                @Override
                protected void onErrorResolved(Throwable e, String msg) {
                    Toast.makeText(getContext(), msg + "", Toast.LENGTH_SHORT).show();
                    if (errorTask != null) {
                        errorTask.run();
                    }
                }
            });
        }
    }

    /**
     * 拒绝通话
     *
     * @param remoteUid  对方ID
     * @param refuseType 拒绝类型：0 挂断 1 忙碌中 2 呼叫方取消
     * @param channelId  频道ID
     * @param ext        扩展参数
     * @param okTask     接口执行成功后需要执行的任务
     * @param errorTask  接口执行失败后执行的任务
     */
    public void refuseCall(String channelId, long remoteUid, int refuseType, Serializable ext, IBaseTask okTask, IBaseTask errorTask) {
        Timber.d("refuseCall >>> channelId:" + channelId + " | remoteUid:" + remoteUid + " | refuseType:" + refuseType + " | ext:" + ext);
        CallRepository.getInstance().refuse(new RefuseRequest(remoteUid, refuseType), new ApiObserver<BaseResult>() {
            @Override
            public void onNext(BaseResult baseResult) {
                super.onNext(baseResult);
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                if (errorTask != null) {
                    errorTask.run();
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建频道
     *
     * @param roomType  类型：8：视频  9：语音
     * @param remoteUid 对方ID
     * @param ext       扩展参数
     * @param okTask    接口执行成功后需要执行的任务
     * @param errorTask 接口执行失败后执行的任务
     */
    public void createChannel(@RoomType int roomType, long remoteUid, Serializable ext, AbstractCreateChannelSuccessTask okTask, AbstractCreateChannelFailureTask errorTask) {
        Timber.d("createChannel >>> roomType:" + roomType + " | remoteUid:" + remoteUid + " | ext:" + ext);
        CallRepository.getInstance().createChannel(new CreateChannelRequest(roomType, String.valueOf(remoteUid)), new ApiObserver<CreateChannelResult>() {
            @Override
            public void onNext(CreateChannelResult createChannelResult) {
                if (okTask != null) {
                    okTask.run(createChannelResult.getData());
                }
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                Toast.makeText(getContext(), msg + "", Toast.LENGTH_SHORT).show();
                boolean runErrorTask = isNotInterceptException(e);
                if (runErrorTask && errorTask != null) {
                    errorTask.run();
                }
                if (CallKit.getInstance().isDebugEnable()) {
                    Timber.e(e, msg);
                }
            }
        });
    }

    /**
     * 送礼物 (该接口后续版本会废弃，目前只有快捷送礼使用，快捷送礼已隐藏)
     *
     * @param context   Context
     * @param userId    收礼人ID
     * @param channelId 频道ID
     * @param giftId    礼物ID
     * @param count     礼物数量
     * @param ext       扩展参数
     */
    public void giveGift(Context context, long userId, long channelId, String giftId, int count, Serializable ext) {
        Timber.d("createChannel >>> userId:" + userId + " | channelId:" + channelId + " | giftId:" + giftId + " | count:" + count + " | ext:" + ext);
        CallRepository.getInstance().giftSend(new GiveGiftRequest(userId, giftId, count, channelId), new ApiObserver<BaseResult>() {
            @Override
            public void onNext(BaseResult baseResult) {
                if (baseResult != null) {
                    if (CallKit.getInstance().isDebugEnable()) {
                        Timber.e(baseResult.toString());
                    }
                }
            }

            @Override
            protected void onErrorResolved(Throwable e, String msg) {
                if (CallKit.getInstance().isDebugEnable()) {
                    Timber.e(e, msg);
                }
            }
        });
    }

    /**
     * 埋点统计
     *
     * @param eventType 事件类型
     * @param ext       扩展参数
     */
    public void logMonitoring(String eventType, Serializable ext) {
        // do nothing
        Timber.d("logMonitoring >>> eventType:" + eventType + " | ext:" + ext);
    }

    /**
     * 检查该异常是否为拦截异常
     *
     * @param e 异常对象
     * @return 非拦截异常 true  拦截异常 false
     */
    private boolean isNotInterceptException(Throwable e) {
        if (e instanceof ApiException) {
            if (mCallService != null) {
                return mCallService.exceptionHandler((ApiException) e);
            }
        } else {
            return true;
        }
        return true;
    }
}