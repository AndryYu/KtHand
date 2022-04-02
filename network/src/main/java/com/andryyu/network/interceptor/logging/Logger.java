package com.andryyu.network.interceptor.logging;

import okhttp3.internal.platform.Platform;

/**
 * @author ihsan on 11/07/2017.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface Logger {
    void log(int level, String tag, String msg);

    Logger DEFAULT = (level, tag, message) -> Platform.get().log(message,level,  null);
}
