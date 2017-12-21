package com.git_leon.selenium.tools;

/**
 * Created by leon on 5/25/17.
 */
public final class StringUtils {
    private StringUtils() {
        /** uninstantiable class */
    }

    public static String capitalizeFirstChar(String str) {
        String firstCharacter = new Character(str.charAt(0)).toString();
        return str.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
    }

    public static String padLeft(Object string, int n) {
        return String.format("%1$" + n + "s", string);
    }

    public static String padRight(Object string, int n) {
        return padLeft(string, -n);
    }

    // removes all specified 'removable' characters from base String
    public static String removeChars(String original, String removableChars) {
        return removeSubstrings(original, removableChars.split(""));
    }

    public static String removeSubstrings(String baseString, String... removeableStrings) {
        for (String removeableString : removeableStrings) {
            baseString = baseString.replace(removeableString, "");
        }
        return baseString;
    }

    public static String repeatString(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    // return String representation of specified integer
    public static String toAlphaString(long number) {
        StringBuilder sb = new StringBuilder();
        while (number-- > 0) {
            sb.append((char) ('a' + (number % 26)));
            number /= 26;
        }
        return sb.reverse().toString();
    }

    @SafeVarargs
    public static <T> String toString(T... vals) {
        StringBuffer sb = new StringBuffer();
        for (T val : vals) {
            sb.append(val);
        }
        return sb.toString();
    }

    // return String representation of specified integer
    public static String getColumnVal(long number) {
        StringBuilder sb = new StringBuilder();
        while (number-- > 0) {
            sb.append((char) ('a' + (number % 26)));
            number /= 26;
        }
        return sb.reverse().toString();
    }
}