package com.basetools.net.service;

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
import com.basetools.model.RefuseRequest;
import com.basetools.model.UpdatePackageRequest;
import com.basetools.model.UpdatePackageResult;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CallService {

    @POST("rocket/one/updatepackage")
    Observable<UpdatePackageResult> checkCallPluginVersion(@Body UpdatePackageRequest request);

    @POST("rocket/one/createChannel")
    Observable<CreateChannelResult> createChannel(@Body CreateChannelRequest request);

    @POST("rocket/one/joinChannel")
    Observable<JoinChannelResult> joinChannel(@Body JoinChannelRequest request);

    @POST("rocket/one/heartBeat")
    Observable<HeartBeatResult> heartbeat(@Body HeartBeatRequest request);

    @POST("rocket/one/heartBeat")
    Observable<HeartBeatResult2> heartbeatV2(@Body HeartBeatRequest request);

    @POST("rocket/one/refuse")
    Observable<BaseResult> refuse(@Body RefuseRequest request);

    @POST("rocket/one/leaveChannel")
    Observable<BaseResult> leaveChannel(@Body LeaveChannelRequest request);

    @POST("rocket/gift/list")
    Observable<GiftListResult> giftList(@Body GiftListRequest request);

    @POST("rocket/gift/send")
    Observable<BaseResult> giftSend(@Body GiveGiftRequest request);

    @POST("rocket/message/sendGift")
    Observable<BaseResult> sendMessageNewGift(@Body GiveGiftMsgRequest request);
}
