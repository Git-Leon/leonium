package com.github.git_leon.leonium;

/**
 * Created by leon on 5/25/17.
 */
public class TimeUtils {
    public static int remainingTime(long baseSeconds, long t0) {
        return (int) (baseSeconds - getElapsedTime(t0));
    }

    public static boolean timeRemains(long baseSeconds, long t0) {
        return remainingTime(baseSeconds, t0) > 0;
    }

    public static double getElapsedTime(double t0) {
        return (System.currentTimeMillis() - t0) / 1000.0;
    }

}
