package com.basetools.listener;

/**
 * 通话插件版本检查回调
 */
public interface CheckCallPluginVersionListener {

    /**
     * 获取插件版本信息成功
     * @param pluginUrl 插件下载地址
     * @param pluginVersion 插件版本号
     */
    void onFetchCallPluginVersionSuccess(String pluginUrl, int pluginVersion);

    /**
     * 获取插件版本信息失败
     * @param code 错误码
     * @param msg 错误信息
     */
    void onFetchCallPluginVersionFailure(int code, String msg);
}
