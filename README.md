# AgoraCallApi

1v1音视频通话公共接口

### 集成

```
 implementation 'io.agora.opensource:1v1-call-api:1.0.0'
```

### 使用

1. 在登录成功后或在获取到登录token后，初始化sdk

```
// 初始化
CallConfig callConfig = new CallConfig.Builder(
        "http://www.google.com/",
        "68e109f0f40ca72a15e05cc22786f8e6",
        "",
        true).build();
// 若项目为海外项目则使用该方法进行初始化
CallKit.initForModule(this, callConfig);
// 若项目为国内项目且使用了插件化技术，则使用该方法进行初始化
CallKit.initForRePlugin(this, callConfig);
```

2. 国内项目集成在宿主中创建固定包名 `com.juzhionline.callplugin.callapi` 并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
海外项目集成在宿主中创建固定包名 `io.agora.opensource.callapi` 并在该包名下创建固定类 ICallServiceImpl.java实现ICallService接口，实现对应方法中业务逻辑。</br>
