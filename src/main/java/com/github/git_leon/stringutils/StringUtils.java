package com.github.git_leon.stringutils;

/**
 * Created by leon on 5/25/17.
 */
public final class StringUtils {
    private StringUtils() {
        /** uninstantiable class */
    }

    /**
     * @param str value to capitalize first character of
     * @return identical string with first character capitalized
     */
    public static String capitalizeFirstChar(String str) {
        String firstCharacter = new Character(str.charAt(0)).toString();
        return str.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
    }

    /**
     * @param string value to be padded
     * @param n      amount of padding
     * @return string with left padding
     */
    public static String padLeft(String string, int n) {
        return String.format("%1$" + n + "s", string);
    }

    /**
     * @param string value to be padded
     * @param n      amount of padding
     * @return string with right padding
     */
    public static String padRight(String string, int n) {
        return padLeft(string, -n);
    }


    /**
     * removes all specified `removable` characters from base String
     *
     * @param original       string to have characters removed from
     * @param removableChars characters to remove from string
     * @return identical string with each of the `removableChars` removed
     */
    public static String removeCharacters(String original, String removableChars) {
        return removeSubstrings(original, removableChars.split(""));
    }

    /**
     * @param baseString       value to have characters removed from
     * @param removableStrings sub-strings to remove from base string
     * @return identical string with each of the sub-strings removed
     */
    public static String removeSubstrings(String baseString, String... removableStrings) {
        for (String removableString : removableStrings) {
            baseString = baseString.replace(removableString, "");
        }
        return baseString;
    }

    /**
     * @param str value to be repeated
     * @param n   number of times to repeat value
     * @return input `str` value concatenated `n` number of times
     */
    public static String repeatString(String str, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * @param number value to get alpha-representation of
     * @return alpha representation of specified integer
     */
    public static String getValue(long number) {
        StringBuilder sb = new StringBuilder();
        while (number-- > 0) {
            sb.append((char) ('a' + (number % 26)));
            number /= 26;
        }
        return sb.reverse().toString();
    }

    /**
     * @param sequences array of CharSequence objects
     * @return a string of each of the CharSequence objects appended
     */
    public static String mergeCharSequenceArray(CharSequence... sequences) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence sequence : sequences) {
            sb.append(sequence.toString());
        }
        return sb.toString();
    }

    /**
     * @param baseString string to be operated on
     * @param substringToRemove string to be removed from `baseString`
     * @param nthOccurrence the index of the sub string to be removed
     * @return string with the `nthOccurrence` of the specified `subStringToRemove` removed
     */
    public static String replaceNthSubstring(String baseString, String substringToRemove, int nthOccurrence) {
        int startIndex = getIndexOfNthSubstring(baseString, substringToRemove, nthOccurrence);
        int endIndex = startIndex + substringToRemove.length();

        String prefix = baseString.substring(0, startIndex);
        String suffix = baseString.substring(endIndex);
        return prefix + suffix;
    }


    /**
     * @param baseString string to be operated on
     * @param substringToRemove string to be removed from `baseString`
     * @return string with the last occurrence of the specified `subStringToRemove` removed
     */
    public static String replaceLastSubString(String baseString, String substringToRemove) {
        return replaceNthSubstring(
                baseString,
                substringToRemove,
                getNumberOfOccurrences(baseString, substringToRemove));
    }

    /**
     * @param baseString string to be operated on
     * @param substring substring to be evaluated from `baseString`
     * @param nthOccurrence the index of the sub string to be removed
     * @return index of the `nthOccurrence` of the specified `substring` removed
     */
    public static int getIndexOfNthSubstring(String baseString, String substring, int nthOccurrence) {
        int pos = baseString.indexOf(substring);
        while (--nthOccurrence > 0 && pos != -1)
            pos = baseString.indexOf(substring, pos + 1);
        return pos;
    }

    /**
     * @param baseString string to be operated on
     * @param substring substring to be evaluated from `baseString`
     * @return number of occurrences of the specified `substring`
     */
    public static int getNumberOfOccurrences(String baseString, String substring) {
        int lastIndex = 0;
        int count = 0;

        while (lastIndex != -1) {
            lastIndex = baseString.indexOf(substring, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += substring.length();
            }
        }
        return count;
    }


}