package com.basetools.net.core;

import com.basetools.CallKit;
import com.basetools.net.interceptor.HeaderInterceptor;
import com.basetools.net.interceptor.NetworkLogInterceptor;
import com.basetools.util.GsonUtils;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit.Builder mRetrofitBuilder;
    private OkHttpClient.Builder mOkHttpClientBuilder;
    private static final int DEFAULT_TIMEOUT = 60;
    private static ApiClient apiClient;

    public static ApiClient getInstance() {
        return getInstance(true);
    }

    public synchronized static ApiClient getInstance(boolean isAddCallAdapterFactory) {
        if (apiClient == null) {
            apiClient = new ApiClient(isAddCallAdapterFactory);
        }
        return apiClient;
    }

    public ApiClient() {
        initApiClient(true);
    }

    public ApiClient(boolean isAddCallAdapterFactory) {
        initApiClient(isAddCallAdapterFactory);
    }

    private void initApiClient(boolean isAddCallAdapterFactory) {
        mRetrofitBuilder = new Retrofit.Builder();
        // String replace = baseHost.replace("https", "http");
        if (isAddCallAdapterFactory) {
            mRetrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        mRetrofitBuilder.baseUrl(CallKit.getInstance().getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(GsonUtils.getGson()))
                .callbackExecutor(new ThreadPoolExecutor(2, 50, 20L,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(64),
                        new ThreadPoolExecutor.DiscardOldestPolicy()));

        mOkHttpClientBuilder = new OkHttpClient.Builder();
        // 添加日志
        mOkHttpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HeaderInterceptor());
        if (CallKit.getInstance().isDebugEnable()) {
            mOkHttpClientBuilder.addNetworkInterceptor(new NetworkLogInterceptor());
        }
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClientBuilder.build();
    }

    public <S> S createApi(String baseUrl, Class<S> ApiClass) {
        mRetrofitBuilder.baseUrl(baseUrl);
        return createApi(ApiClass);
    }

    public <S> S createApi(Class<S> ApiClass) {
        return mRetrofitBuilder
                .client(mOkHttpClientBuilder.build())
                .build()
                .create(ApiClass);
    }
}
