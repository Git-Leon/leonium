package com.git_leon.selenium.tools;

/**
 * @author leon on 5/25/18.
 */

import java.awt.*;
import java.util.Date;
import java.util.Random;

/**
 * @author leon.hunter
 */
public final class RandomUtils {
    private static volatile Random random = new Random();

    private RandomUtils() {
    }

    /**
     * @param maxRed maximum saturation of red; 255 max
     * @param maxGreen maximum saturation of green; 255 max
     * @param maxBlue maximum saturation of blue; 255 max
     * @return a random color within the specified RGB range
     */
    public static Color createColor(int maxRed, int maxGreen, int maxBlue) {
        int red = createInteger(0, maxRed);
        int green = createInteger(0, maxGreen);
        int blue = createInteger(0, maxBlue);
        return new Color(red, green, blue);
    }

    /**
     * @param percentage likelihood of returning true
     * @return true or false
     */
    public static boolean createBoolean(float percentage) {
        return percentage > createFloat(0, 100);
    }

    /**
     * @param min minimum ascii value
     * @param max maximum ascii value
     * @return a random character between the specified min and max character range
     */
    public static Character createCharacter(char min, char max) {
        return (char) createInteger((int) min, (int) max).intValue();
    }

    /**
     * @param min minimum double value
     * @param max maximum double value
     * @return a random double between the specified minimum and maximum numeric range
     */
    public static Double createDouble(Double min, Double max) {
        return createFloat(min.floatValue(), max.floatValue()).doubleValue();
    }

    /**
     * @param min minimum float value
     * @param max maximum float value
     * @return a random float between the specified minimum and maximum numeric range
     */
    public static synchronized Float createFloat(float min, float max) {
        return random.nextFloat() * (max - min) + min;
    }

    /**
     * @param min minimum integer value
     * @param max maximum integer value
     * @return a random integer between the specified minimum and maximum numeric range
     */
    public static Integer createInteger(Integer min, Integer max) {
        return createFloat(min, max).intValue();
    }

    /**
     * @param min minimum long value
     * @param max maximum long value
     * @return a random long between the specified minimum and maximum numeric range
     */
    public static Long createLong(Long min, Long max) {
        return createFloat(min, max).longValue();
    }

    /**
     * @param min minimum character to be generated in string
     * @param max maximum character value to be generated in string
     * @param length length of string
     * @return a random string of the specified length containing characters in the specified range
     */
    public static String createString(char min, char max, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(createCharacter(min, max));
        }
        return sb.toString();
    }

    /**
     * @param array an array to select a random element from
     * @param <AnyType> any type
     * @return a randomly selected element from the specified array
     */
    public static <AnyType> AnyType selectElement(AnyType[] array) {
        return array[createInteger(0, array.length - 1)];
    }

    /**
     * @param minYear minimum year-value to be generated
     * @param maxYear maximum year-value to be generated
     * @return a random Date value within the specified min and max year
     */
    public static Date createDate(int minYear, int maxYear) {
        Date minDate = new Date(minYear, 1,1);
        Date maxDate = new Date(maxYear, 1,1);
        return createDate(minDate, maxDate);
    }

    /**
     * @param minDate minimum Date to be returned
     * @param maxDate minimum Date to be returned
     * @return random date between the specified `minDate` and `maxDate`
     */
    public static Date createDate(Date minDate, Date maxDate) {
        long randomTimeInSpecifiedRange = createLong(minDate.getTime(), maxDate.getTime());
        return new Date(randomTimeInSpecifiedRange);
    }
}
