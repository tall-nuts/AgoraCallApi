package com.basetools.net.interceptor;

import android.text.TextUtils;
import com.basetools.CallKit;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // 追加设置header信息
        final Request originalRequest = chain.request();
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest);
        }

        final String token = CallKit.getInstance().getToken();
        Request request = originalRequest
                .newBuilder()
                .addHeader("token", TextUtils.isEmpty(token) ? "" : token)
                .addHeader("domain", CallKit.getInstance().getBaseUrl())
                .addHeader("timestamp", String.valueOf(System.currentTimeMillis()))
                .build();
        return chain.proceed(request);
    }
}