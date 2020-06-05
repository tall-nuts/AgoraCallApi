package com.basetools.util;

import android.util.Log;
import java.util.Random;

public final class Timber {

    private static final String TAG = Timber.class.getSimpleName();
    private static final String[] LOG_PRE = {"🎉", "🍭", "🎃", "🐶", "🐣", "🚀", "💋", "👽", "💌",
            "♨️", "🤖", "🐲", "🍄", "💫", "📌", "💞", "💛", "💚", "💙", "💜", "🧡"};
    private static boolean mDebugEnable;
    private static Random mRandom = new Random();

    public static void setDebugEnable(boolean debugEnable) {
        mDebugEnable = debugEnable;
    }

    public static void v(String message) {
        v(TAG, message);
    }

    public static void v(String tag, String message) {
        if (mDebugEnable) {
            Log.v(tag, LOG_PRE[mRandom.nextInt(LOG_PRE.length)] + message);
        }
    }

    public static void d(String message) {
        d(TAG, message);
    }

    public static void d(String tag, String message) {
        if (mDebugEnable) {
            Log.d(tag, LOG_PRE[mRandom.nextInt(LOG_PRE.length)] + message);
        }
    }

    public static void i(String message) {
        i(TAG, message);
    }

    public static void i(String tag, String message) {
        if (mDebugEnable) {
            Log.i(tag, LOG_PRE[mRandom.nextInt(LOG_PRE.length)] + message);
        }
    }

    public static void w(String message) {
        w(TAG, message);
    }

    public static void w(String tag, String message) {
        if (mDebugEnable) {
            Log.w(tag, LOG_PRE[mRandom.nextInt(LOG_PRE.length)] + message);
        }
    }

    public static void e(String message) {
        e(TAG, message);
    }

    public static void e(String tag, String message) {
        if (mDebugEnable) {
            Log.e(tag, LOG_PRE[mRandom.nextInt(LOG_PRE.length)] + message);
        }
    }

    public static void e(Throwable e, String message) {
        if (mDebugEnable) {
            e.printStackTrace();
            e(message);
        }
    }
}
