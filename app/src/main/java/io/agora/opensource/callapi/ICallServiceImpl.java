package io.agora.opensource.callapi;

import android.content.Context;
import android.widget.ImageView;
import com.basetools.api.ICallService;
import com.basetools.constant.RoomType;
import com.basetools.model.UserInfo;
import com.basetools.net.core.ApiException;

import java.io.Serializable;

public class ICallServiceImpl implements ICallService {

    @Override
    public UserInfo getCurrentUserInfo() {
        return null;
    }

    @Override
    public boolean videoCallCameraDefaultOpen() {
        return false;
    }

    @Override
    public int getDiamondBalance() {
        return 0;
    }

    @Override
    public void updateDiamondBalance(int balance) {

    }

    @Override
    public void getCallPricePer(long hostUid, FetchCallPriceListener listener) {

    }

    @Override
    public String getGiftSvgaPath(String giftId) {
        return null;
    }

    @Override
    public void getGiftIconAsyn(String giftId, DownloadGiftDrawableListener listener) {

    }

    @Override
    public void loadImage(Context context, String path, ImageView view) {

    }

    @Override
    public void loadRoundedImage(Context context, String imgUrl, ImageView imageView, int corners) {

    }

    @Override
    public void showRechargeDialog(Context context, long userId, long roomId) {

    }

    @Override
    public void showGiveGiftDialog(Context context, long userId, long roomId, String nickname, String avatar) {

    }

    @Override
    public int getVideoProfile() {
        return 0;
    }

    @Override
    public boolean exceptionHandler(ApiException exception) {
        return false;
    }

    @Override
    public void sendPrivateMessage(String remoteUid, String remoteNickname, String remoteAvatar, String message, Serializable ext) {

    }

    @Override
    public void onCallConnected(String channelId, Serializable serializable) {

    }

    @Override
    public void onRandomMatchStatusChanged(int status, int roomType) {

    }

    @Override
    public void onCallActivityFinish(@RoomType int roomType, long remoteUid, String remoteNickname, String remoteAvatar, Serializable ext) {

    }
}
