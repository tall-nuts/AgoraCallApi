package com.basetools.net;

import java.io.File;

public interface DownloadListener {
    void onFinish(File file);

    void onProgress(int progress);

    void onFailed(String errMsg);
}