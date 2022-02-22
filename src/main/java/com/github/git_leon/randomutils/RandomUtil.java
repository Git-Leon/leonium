package com.github.git_leon.randomutils;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RandomUtil {
    private final Random random;

    public RandomUtil(Long seed) {
        this(new Random(seed));
    }

    public RandomUtil() {
        this(new Random());
    }

    public RandomUtil(Random random) {
        this.random = random;
    }

    /**
     * @return true with the likelihood of specified percentage
     */
    public boolean createBoolean(float percentage) {
        return percentage > createDouble(0, 100);
    }

    /**
     * @return a random character between the specified min and max character range
     */
    public Character createCharacter(char min, char max) {
        return (char) createInteger((int) min, (int) max).intValue();
    }

    /**
     * @return a random double between the specified min and max numeric range
     */
    public Float createFloat(float min, float max) {
        return createDouble(min, max).floatValue();
    }

    /**
     * @return a random float between the specified min and max numeric range
     */
    public Double createDouble(double min, double max) {
        return random.nextDouble() * (max - min) + min;
    }

    /**
     * @return a random integer between the specified min and max numeric range
     */
    public Integer createInteger(int min, int max) {
        return createDouble(min, max).intValue();
    }

    /**
     * @return a random long between the specified min and max numeric range
     */
    public Long createLong(long min, long max) {
        return createDouble(min, max).longValue();
    }

    /**
     * @return a random string of the specified length containing characters in the specified range
     */
    public String createString(char min, char max, int stringLength) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            sb.append(createCharacter(min, max));
        }
        return sb.toString();
    }

    /**
     * @return an array of random string objects of the specified length containing characters in the specified range
     */
    public String[] createStrings(char min, char max, int stringLength, int stringCount) {
        String[] strings = new String[stringCount];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = createString(min, max, stringLength);
        }
        return strings;
    }

    /**
     * @param minYear minimum year-value to be generated
     * @param maxYear maximum year-value to be generated
     * @return a random Date value within the specified min and max year
     */
    public Date createDate(Number minYear, Number maxYear) {
        assert minYear.doubleValue() < maxYear.doubleValue();
        Date minDate = new Date(minYear.intValue(), 1, 1);
        Date maxDate = new Date(maxYear.intValue(), 1, 1);
        return createDate(minDate, maxDate);
    }

    /**
     * @param minDate minimum Date to be returned
     * @param maxDate minimum Date to be returned
     * @return random date between the specified `minDate` and `maxDate`
     */
    public Date createDate(Date minDate, Date maxDate) {
        return new Date(createLong(minDate.getTime(), maxDate.getTime()));
    }

    /**
     * @param array     an array to select a random element from
     * @param <AnyType> any type
     * @return a randomly selected element from the specified array
     */
    public <AnyType> AnyType selectElement(AnyType[] array) {
        return selectElement(Arrays.asList(array));
    }

    /**
     * @param list      an array to select a random element from
     * @param <AnyType> any type
     * @return a randomly selected element from the specified array
     */
    public <AnyType> AnyType selectElement(List<AnyType> list) {
        Integer randomIndex = createInteger(0, list.size() - 1);
        AnyType randomElement = list.get(randomIndex);
        return randomElement;
    }

    /**
     * @return specified string value with random upper and lower casing assigned to each character
     */
    public String shuffleCasing(String str) {
        StringBuffer sb = new StringBuffer();
        for (String s : str.toLowerCase().split("")) {
            sb.append(createBoolean(50) ? s.toUpperCase() : s.toLowerCase());
        }
        return sb.toString();
    }

    /**
     * @return shuffles the specified string array
     */
    public <AnyType> AnyType[] shuffleArray(AnyType[] array) {
        ArrayList<AnyType> list = new ArrayList<>(Arrays.asList(array));
        Collections.shuffle(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * @return a random color with the specified maximum rgb values
     */
    public Color createColor(int maxRed, int maxGreen, int maxBlue) {
        int red = createInteger(0, maxRed);
        int green = createInteger(0, maxGreen);
        int blue = createInteger(0, maxBlue);
        return new Color(red, green, blue);
    }
}
