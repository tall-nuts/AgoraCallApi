package io.agora.opensource.callapi;

import android.content.Context;
import android.widget.ImageView;
import com.basetools.api.ICallService;
import java.io.Serializable;

public class ICallServiceImpl implements ICallService {

    @Override
    public int getGender() {
        return 0;
    }

    @Override
    public boolean isAnchor() {
        return false;
    }

    @Override
    public boolean isVip() {
        return false;
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
    public void getGiftIcon(String giftId, DownloadGiftDrawableListener listener) {

    }

    @Override
    public void loadImage(Context context, String path, ImageView view) {

    }

    @Override
    public void loadRoundedImage(Context context, String imgUrl, ImageView imageView, int corners) {

    }

    @Override
    public void startCallInviteSound(boolean isLoop) {

    }

    @Override
    public void stopCallInviteSound() {

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
    public long getUserId() {
        return 0;
    }

    @Override
    public void interceptVip() {

    }

    @Override
    public void interceptBalance() {

    }

    @Override
    public void sendPrivateMessage(String remoteUid, String remoteNickname, String remoteAvatar, String message) {

    }

    @Override
    public void sendPrivateMessage(String remoteUid, String remoteNickname, String remoteAvatar, String message, Serializable ext) {

    }

    @Override
    public void onCallActivityFinish(long remoteUid, String remoteNickname, String remoteAvatar) {

    }

    @Override
    public void onCallActivityFinish(long remoteUid, String remoteNickname, String remoteAvatar, Serializable ext) {

    }
}
