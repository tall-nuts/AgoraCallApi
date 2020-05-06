package com.basetools.net.core;

import com.basetools.CallKit;
import com.basetools.util.Timber;
import org.json.JSONException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Locale;
import io.reactivex.observers.DisposableObserver;
import retrofit2.HttpException;

public abstract class ApiObserver<T> extends DisposableObserver<T> {

    //HTTP的状态码
    private static final int HTTP_BAD_REQUEST = 400;
    private static final int HTTP_TOKEN_EXPIRED = 401; // token 过期
    private static final int HTTP_FORBIDDEN = 403;
    private static final int HTTP_NOT_FOUND = 404;
    private static final int HTTP_TIMEOUT = 408;
    private static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    //出错提示
    private static final String MSG_TOKEN_EXPIRED = "expired,Please login again";
    private static final String MSG_NETWORK_ERROR = "network error";
    private static final String MSG_NETWORK_CONNECTION_ERROR = "network connection error ,please check it or try again later";
    private static final String MSG_UNKNOWN_ERROR = "network error";
    private static final String MSG_TIME_OUT = "network timeout";
    private static final String MSG_SERVER_ERROR = "server error";
    private static final String MSG_JSON_PARSE_ERROR = "data prasing error";

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onComplete() {
        onFinally();
    }

    @Override
    public void onError(Throwable e) {
        resolveException(e);
        onFinally();
    }

    public void onFinally() {
    }

    private void resolveException(Throwable e) {
        if (CallKit.getInstance().isDebugEnable()) {
            e.printStackTrace();
        }
        boolean isConnected = true;
        if (e instanceof ApiException) {
            String msg = ((ApiException) e).getMsg();
            if (msg == null || msg.isEmpty()) {
                if (CallKit.getInstance().isDebugEnable()) {
                    msg = String.format(Locale.CHINA, "Unknown error！Code：%d", ((ApiException) e).getCode());
                } else {
                    msg = "";
                }
            }

            onErrorResolved(e, msg);
            isConnected = true;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case HTTP_BAD_REQUEST:
                case HTTP_FORBIDDEN:
                case HTTP_NOT_FOUND:
                case HTTP_INTERNAL_SERVER_ERROR:
                    onErrorResolved(e, MSG_SERVER_ERROR);
                    isConnected = true;
                    break;
                case HTTP_TOKEN_EXPIRED:
                    onErrorResolved(e, MSG_TOKEN_EXPIRED);
                    isConnected = true;
                    break;
                case HTTP_TIMEOUT:
                    onErrorResolved(e, MSG_TIME_OUT);
                    isConnected = false;
                    break;
                default:
                    onErrorResolved(e, MSG_NETWORK_ERROR);
                    isConnected = false;
                    break;
            }
        } else if (e instanceof SocketTimeoutException) {
            onErrorResolved(e, MSG_TIME_OUT);
            isConnected = false;
        } else if (e instanceof ConnectException) {
            onErrorResolved(e, MSG_NETWORK_ERROR);
            isConnected = false;
        } else if (e instanceof UnknownHostException) {
            onErrorResolved(e, MSG_NETWORK_CONNECTION_ERROR);
            isConnected = false;
        } else if (e instanceof SocketException) {
            onErrorResolved(e, MSG_SERVER_ERROR);
            isConnected = false;
        }/* else if (e instanceof JsonSyntaxException) {
            onErrorResolved(e, MSG_JSON_PARSE_ERROR);
        } */ else if (e instanceof JSONException) {
            onErrorResolved(e, MSG_JSON_PARSE_ERROR);
            isConnected = true;
        } else {
            onErrorResolved(e, MSG_UNKNOWN_ERROR);
            isConnected = true;
        }

        if (!isConnected) {
            Timber.e("Network connection failed!!!");
        }
    }

    protected abstract void onErrorResolved(Throwable e, String msg);
}