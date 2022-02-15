package com.github.git_leon.logging;

import java.util.concurrent.TimeUnit;

/**
 * @author leon on 5/26/18.
 */
public final class SleepUtils {
    public static void wait(int timeout, TimeUnit timeUnit) {
        try {
            Thread.sleep(timeUnit.convert(timeout, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            throw new Error(e);
        }
    }
}