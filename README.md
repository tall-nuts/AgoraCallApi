# AgoraCallApi

1v1音视频通话公共接口

![](https://travis-ci.org/pengfeigao/AgoraCallApi.svg?branch=master) [ ![Download](https://api.bintray.com/packages/akid/maven/1v1-call-api/images/download.svg) ](https://bintray.com/akid/maven/1v1-call-api/_latestVersion)
---

### 集成

```
 implementation 'io.agora.opensource:1v1-call-api:1.0.14'
```

### 使用

1. 在登录成功后或在获取到登录token后，初始化sdk

```
// 初始化
CallConfig callConfig = new CallConfig.Builder(
        "http://www.google.com/", // host url
        "68e109f0f40ca72a15e05cc22786f8e6", // token
        "", // platform json data
        true) // is global integration
        .setDebugEnable(true) // isDebug ? output log
        .setFixHeartbeatApi(true) // heartbeat v2
        .build();
// module方式集成，使用该方法初始化
CallKit.initForModule(this, callConfig);
// 插件化 方式集成，使用该方法初始化
CallKit.initForRePlugin(this, callConfig);
```

2. 国内项目集成在宿主中创建固定包名 `com.juzhionline.callplugin.callapi` 并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
海外项目集成在宿主中创建固定包名 `io.agora.opensource.callapi` 并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>

---

### 注：

由于CallActivity会有最小化功能，客户端在实现ICallService接口逻辑代码中出现的宿主Activity需要指定为singleTask启动模式，且taskAffinity为 ":common"（MainActivity除外）。

### CHANGELOG

##### [1.0.14] - [2020-06-05]

##### Change:

- ICallService 新增 onCallActivityMinimize() 回调方法

##### [1.0.13] - [2020-06-04]

##### Change:

- CallKit randomMatch() 代码逻辑修改

##### [1.0.12] - [2020-06-03]

##### Change:

- ICallService exceptionHandler() -> processException() 

##### [1.0.11] - [2020-06-03]

##### Change:

- ICallService onCallActivityFinish方法中新增boolean cancelMatch 是否取消匹配字段

##### [1.0.10] - [2020-06-03]

##### Change:

- ICallService onCallActivityFinish方法中新增roomType通话类型

##### [1.0.9] - [2020-06-02]

##### Fixed:

- 修复CallKit createRandomMatchIntentForGlobalModule() ComponentName设置错误。
- 修复Timber打印日志重复，使用系统日志打印。

##### [1.0.8] - [2020-06-01]

##### Change:

- 废弃ICallService中有关当前用户基本信息的api 方法，使用 UserInfo getCurrentUserInfo() 方法替代。
- 更改getGiftIcon(String giftId, DownloadGiftDrawableListener listener) -> getGiftIconAsyn(String giftId, DownloadGiftDrawableListener listener) 方法名。

##### [1.0.7] - [2020-05-30]

##### Change: 

- 废弃ICallService中 interceptVip() interceptBalance() interceptNobleStar() 业务拦截api，使用exceptionHandler(ApiException e) 方法替代。
- 新增随机匹配相关api。

##### [1.0.6] - [2020-05-29]

##### Change: 

- RoomType 新增 随机视频：RANDOM_VIDEO_CALL = 11 随机语音：ANDOM_VOICE_CALL = 12。

##### [1.0.5] - [2020-05-23]

##### Fixed:

- 修复心跳响应中返回的ext对象解析问题。

##### [1.0.4] - [2020-05-08]

##### Fixed:

- CallConfig.Builder增加setFixHeartbeatApi() api用于修复服务端根据不同渠道返回心跳数据结构不同。
