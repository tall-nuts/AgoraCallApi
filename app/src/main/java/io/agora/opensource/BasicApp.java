package io.agora.opensource;

import android.app.Application;
import com.basetools.CallKit;
import com.basetools.net.config.CallConfig;

public class BasicApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        CallConfig callConfig = new CallConfig.Builder(
                "http://www.google.com/",
                "68e109f0f40ca72a15e05cc22786f8e6",
                "",
                true).build();
        CallKit.initForModule(this, callConfig);
    }
}
