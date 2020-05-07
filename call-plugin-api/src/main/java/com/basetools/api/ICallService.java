package com.basetools.api;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.io.Serializable;

/**
 * 集成方法：
 * </br>
 * 1. 宿主按照RePlugin集成文档正常操作。</br>
 * 2. 国内项目集成在宿主中创建固定包名 "com.juzhionline.callplugin.callapi"并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
 * 2. 海外项目集成在宿主中创建固定包名 "io.agora.opensource.callapi"并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
 * </br>
 * 说明：该module所编译生成的jar包同时会在callPlugin插件中引入依赖，插件中会通过反射调用ICallServiceImpl.java类进行使用。</br>
 *
 * @author gaopengfei on 2019/12/07.
 * @version v1.0.19
 */
public interface ICallService {

    /**
     * 获取当前用户性别
     * @return
     */
    int getGender();

    /**
     * 是否是主播
     * @return
     */
    boolean isAnchor();

    /**
     * 是否是VIP用户
     * @return
     */
    boolean isVip();

    /**
     * 视频通话摄像头默认是否开启
     * @return
     */
    boolean videoCallCameraDefaultOpen();

    /**
     * 获取当前用户钻石余额
     * @return
     */
    int getDiamondBalance();

    /**
     * 更新钻石余额
     */
    void updateDiamondBalance(int balance);

    /**
     * 获取视频通话价格 钻石/分钟
     * @return
     */
    void getCallPricePer(long hostUid, FetchCallPriceListener listener);

    /**
     * 获取特效礼物SVGA路径
     * @param giftId 礼物ID
     * @return
     */
    String getGiftSvgaPath(String giftId);

    /**
     * 获取礼物Drawable
     * @param giftId 礼物ID
     * @return
     */
    void getGiftIcon(String giftId, DownloadGiftDrawableListener listener);

    /**
     * 加载图片
     * @param context
     * @param path
     * @param view
     */
    void loadImage(Context context, String path, ImageView view);

    /**
     * 加载圆角图片
     * @param context Context
     * @param imgUrl 图片地址
     * @param imageView ImageView
     * @param corners 圆角角度
     */
    void loadRoundedImage(Context context, String imgUrl, ImageView imageView, int corners);

    /**
     * 播放呼叫提示音
     * @param isLoop 是否循环播放
     */
    void startCallInviteSound(boolean isLoop);

    /**
     * 停止播放提示音
     */
    void stopCallInviteSound();

    /**
     * 展示充值弹窗
     * @param context Context
     * @param userId 用户ID
     * @param roomId 频道ID
     */
    void showRechargeDialog(Context context, long userId, long roomId);

    /**
     * 展示送礼物弹窗
     * @param context Context
     * @param userId 用户ID
     * @param roomId 频道ID
     * @param nickname 对方昵称
     * @param avatar 对方头像
     */
    void showGiveGiftDialog(Context context, long userId, long roomId, String nickname, String avatar);

    /**
     * 1V1视频分辨率参数
     * @return
     */
    int getVideoProfile();

    /**
     * 获取当前用户ID
     * @return
     */
    long getUserId();

    /**
     * VIP拦截
     */
    void interceptVip();

    /**
     * 余额|钻石拦截
     */
    void interceptBalance();

    /**
     * 发私信
     * @param remoteUid 收信人ID
     * @param remoteNickname 收信人昵称
     * @param remoteAvatar 收信人头像
     * @param message 消息内容
     * @param ext 扩展参数
     */
    void sendPrivateMessage(String remoteUid, String remoteNickname, String remoteAvatar, String message, Serializable ext);

    /**
     * 通话已建立
     * @param channelId 频道ID
     * @param serializable 扩展信息
     */
    void onCallConnected(String channelId, Serializable serializable);

    /**
     * 1V1通话页面关闭
     * @param remoteUid 对方ID
     * @param remoteNickname 对方昵称
     * @param remoteAvatar 对方头像
     * @param ext 扩展参数
     */
    void onCallActivityFinish(long remoteUid, String remoteNickname, String remoteAvatar, Serializable ext);

    interface DownloadGiftDrawableListener{
        void onDownloadSuccess(Drawable drawable);
    }

    interface FetchCallPriceListener{
        void onFetchCallPriceSuccess(int videoCallPricePer, int voiceCallPrice);
    }
}