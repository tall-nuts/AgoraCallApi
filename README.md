# AgoraCallApi

1v1音视频通话公共接口

![](https://travis-ci.org/pengfeigao/AgoraCallApi.svg?branch=master) [ ![Download](https://api.bintray.com/packages/akid/maven/1v1-call-api/images/download.svg) ](https://bintray.com/akid/maven/1v1-call-api/_latestVersion)
---

##### 集成

```
 implementation 'io.agora.opensource:1v1-call-api:1.0.1'
```

##### 使用

1. 在登录成功后或在获取到登录token后，初始化sdk

```
// 初始化
CallConfig callConfig = new CallConfig.Builder(
        "http://www.google.com/", // host url
        "68e109f0f40ca72a15e05cc22786f8e6", // token
        "", // platform json data
        true) // is global integration
        .build();
// module方式集成，使用该方法初始化
CallKit.initForModule(this, callConfig);
// 插件化 方式集成，使用该方法初始化
CallKit.initForRePlugin(this, callConfig);
```

2. 国内项目集成在宿主中创建固定包名 `com.juzhionline.callplugin.callapi` 并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
海外项目集成在宿主中创建固定包名 `io.agora.opensource.callapi` 并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
