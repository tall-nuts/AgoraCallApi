package com.basetools.model;

import com.basetools.CallKit;
import com.basetools.util.GsonUtils;

/**
 * 检测更新请求
 */
public class UpdatePackageRequest {

    private Platform platform;

    public UpdatePackageRequest() {
        this.platform = GsonUtils.fromJson(CallKit.getInstance().getPlatformJson(), Platform.class);
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "UpdatePackageRequest{" +
                "platform=" + platform +
                '}';
    }
}
