package com.basetools.net.repository;

import com.basetools.model.BaseResult;
import com.basetools.model.CreateChannelRequest;
import com.basetools.model.CreateChannelResult;
import com.basetools.model.GiftListRequest;
import com.basetools.model.GiftListResult;
import com.basetools.model.GiveGiftRequest;
import com.basetools.model.GiveGiftMsgRequest;
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
import com.basetools.net.core.ApiClient;
import com.basetools.net.core.ApiException;
import com.basetools.net.core.ApiObserver;
import com.basetools.net.rx.RxSchedulers;
import com.basetools.net.service.CallService;

/**
 * 音视频通话数据请求
 */
public class CallRepository {

    private static class SingletonHolder {
        private static final CallRepository sInstance = new CallRepository();
    }

    public static CallRepository getInstance() {
        return SingletonHolder.sInstance;
    }

    /**
     * 检测插件版本信息
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void checkCallPluginVersion(UpdatePackageRequest request, ApiObserver<UpdatePackageResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.checkCallPluginVersion(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 创建频道
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void createChannel(CreateChannelRequest request, ApiObserver<CreateChannelResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.createChannel(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 加入频道
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void joinChannel(JoinChannelRequest request, ApiObserver<JoinChannelResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.joinChannel(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 心跳
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void heartbeat(HeartBeatRequest request, ApiObserver<HeartBeatResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.heartbeat(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 心跳
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void heartbeatV2(HeartBeatRequest request, ApiObserver<HeartBeatResult2> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.heartbeatV2(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 离开频道
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void leaveChannel(LeaveChannelRequest request, ApiObserver<BaseResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.leaveChannel(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 取消|拒绝
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void refuse(RefuseRequest request, ApiObserver<BaseResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.refuse(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 礼物列表
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void giftList(GiftListRequest request, ApiObserver<GiftListResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.giftList(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 通话中赠送礼物
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void giftSend(GiveGiftRequest request, ApiObserver<BaseResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.giftSend(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 发送礼物信
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void sendGiftMessage(GiveGiftMsgRequest request, ApiObserver<BaseResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.sendMessageNewGift(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }

    /**
     * 随机匹配
     * @param request 请求参数
     * @param apiObserver 回调
     */
    public void randomRoom(RandomRoomRequest request, ApiObserver<RandomRoomResult> apiObserver){
        CallService callService = ApiClient.getInstance().createApi(CallService.class);
        callService.randomRoom(request).map(bridge -> {
            if (bridge == null) {
                throw new ApiException(ApiException.CODE_FAILED, "Fetch data failure!");
            } else if (bridge.getCode() != 1) {
                throw new ApiException(bridge.getCode(), bridge.getMsg());
            }
            return bridge;
        }).compose(RxSchedulers.apply()).subscribe(apiObserver);
    }
}
