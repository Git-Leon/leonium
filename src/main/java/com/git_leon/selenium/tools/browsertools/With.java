package com.git_leon.selenium.tools.browsertools;

/**
 * @author leon on 4/10/18.
 */

import org.openqa.selenium.By;

/**
 * @purpose Mechanism to locate elements within a document
 */
public final class With {
    private With() {}

    /**
     * @param text The value of the "text" to search for
     * @return a By which locates button elements by the value of the text
     */
    public static By buttonText(String text) {
        return tagAndText("button", text);
    }

    /**
     * @param attribute the name of the attribute to search for
     * @param value the value of the attribute to search for
     * @return a By object which locates elements by the value of the attribute
     */
    public static By attributeValue(String attribute, String value) {
        return tagAttributeValue("*", attribute, value);
    }

    /**
     * @param attribute the value of the attribute to search for
     * @return a By object which locates elements by the value of the attribute.
     */
    public static By attribute(String attribute) {
        return tagAttribute("*", attribute);
    }

    /**
     * @param text The value of the text to search for
     * @return a By which locates elements by the value of the text
     */
    public static By text(String text) {
        return tagAndText("*", text);
    }

    /**
     * @param tag The tag to search for
     * @param text The text-value to search for
     * @return a By which locates button elements by the tag and text-value
     */
    public static By tagAndText(String tag, String text) {
        return By.xpath(String.format(".//%s[contains(text(), '%s')]", tag, text));
    }

    /**
     * @param tag the tag to search for
     * @param attribute the name of the attribute to search for
     * @param value the value of the attribute to search for
     * @return a By object which locates elements with specified tag and value of the attribute
     */
    public static By tagAttributeValue(String tag, String attribute, String value) {
        return By.xpath(String.format(".//%s[@%s='%s']", tag, attribute, value));
    }

    /**
     * @param tag the tag to search for
     * @param attribute the name of the attribute to search for
     * @return a By object which locates elements with specified tag and attribute
     */
    public static By tagAttribute(String tag, String attribute) {
        return By.xpath(String.format(".//%s['%s']", tag, attribute));
    }
}