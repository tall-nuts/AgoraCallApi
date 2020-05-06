package com.basetools.net.interceptor;

import android.os.Build;

import com.basetools.util.Timber;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

public class NetworkLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long t1 = System.nanoTime();//请求发起的时间
        String rSubtype = null;
        final RequestBody requestBody = request.body();
        if (requestBody != null && requestBody.contentType() != null) {
            rSubtype = requestBody.contentType().subtype();
        }

        if (isNotFileRequest(rSubtype)) {
            Timber.d("Request: %s on %s%nRequest Params:%s %n%s",
                    request.url(),
                    chain.connection(),
                    bodyToString(request),
                    request.headers());
        }

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();//收到响应的时间
        //不能直接使用response.body（）.string()的方式输出日志
        //因为response.body().string()之后，response中的流会被关闭，程序会报错，
        // 我们需要创建出一个新的response给应用层处理
        ResponseBody responseBody = response.peekBody(1024 * 1024);
        if (responseBody != null) {
            // final String content = responseBody.string();
            final String content = uncompress(responseBody.bytes());
            Timber.d("Response: [%s] %nResponse data:%s  %.1fms%n%s",
                    response.request().url(),
                    content,
                    (t2 - t1) / 1e6d,
                    response.headers());
        } else {
            Timber.d("responseBody is null");
        }
        return response;
    }

    private boolean isNotFileRequest(final String subtype) {
        return subtype != null && (subtype.contains("json")
                || subtype.contains("xml")
                || subtype.contains("plain")
                || subtype.contains("html"));
    }

    private String bodyToString(final Request copy) {
        try {
            final Buffer buffer = new Buffer();
            if (copy.body() == null)
                return "";
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
            // return uncompress(buffer.readByteArray());
        } catch (final IOException e) {
            return "{\"err\": \"" + e.getMessage() + "\"}";
        }
    }

    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }

            @Override
            public long contentLength() {
                return -1; // 无法知道压缩后的数据大小
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }

    /**
     * GZIP内容解压缩
     *
     * @param str
     * @return
     * @throws IOException
     */
    public String uncompress(byte str[]) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str))) {
            int b;
            while ((b = gis.read()) != -1) {
                baos.write((byte) b);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return new String(baos.toByteArray(), StandardCharsets.UTF_8);
        }
        return new String(baos.toByteArray());
    }
}
