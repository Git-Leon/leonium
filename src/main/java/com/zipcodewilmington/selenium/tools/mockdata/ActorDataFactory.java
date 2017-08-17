package com.zipcodewilmington.selenium.tools.mockdata;

import com.zipcodewilmington.selenium.tools.RandomUtils;
import com.zipcodewilmington.selenium.tools.StringUtils;

/**
 * Created by leon on 8/17/17.
 */
public class ActorDataFactory {
    public static Integer createSocialSecurityNumber() {
        return RandomUtils.createInteger(10000000, 999999999);
    }

    public static String createUniqueName() {
        String name = StringUtils.getColumnVal(System.nanoTime());
        String firstChar = Character.toString(name.charAt(0));
        return name.replaceFirst(firstChar, firstChar.toUpperCase());
    }

    public static String createAddressLine() {
        return String.format("%s %s Rd", createUniqueName(), createUniqueName());
    }

    public static String createEmailAddress(String firstName, String lastName) {
        String milliseed = Long.toString(System.currentTimeMillis());
        return String.format("%s.%s_%s@pobox.com", firstName, lastName, milliseed);
    }
}
