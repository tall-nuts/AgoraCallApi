package com.basetools.net;

import androidx.annotation.NonNull;

public class Resource<T> {

    @NonNull
    private final int mStatus;
    private final T mData;
    private final Exception mException;

    private Resource(@NonNull int status, T data, Exception exception) {
        this.mStatus = status;
        this.mData = data;
        this.mException = exception;
    }

    public static <T> Resource<T> loading(T data) {
        return new Resource<>(Status.LOADING, data, null);
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(Exception e) {
        return new Resource<>(Status.ERROR, null, e);
    }

    public static <T> Resource<T> empty() {
        return new Resource<>(Status.EMPTY, null, null);
    }

    @NonNull
    public int getStatus() {
        return mStatus;
    }

    public T getData() {
        return mData;
    }

    public Exception getException() {
        return mException;
    }

    public static class Status {
        public static final int LOADING = 1;
        public static final int SUCCESS = LOADING + 1;
        public static final int ERROR = SUCCESS + 1;
        public static final int EMPTY = ERROR + 1;
        public static final int APOLLO_USER_NOT_EXIST = 1001;//google用户不存在  专用于google注册
    }
}