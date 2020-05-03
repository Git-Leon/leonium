package com.git_leon.leonium.browsertools.browserhandler.waiting;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

public final class ExpectedBrowserCondition {
    private ExpectedBrowserCondition() {}

    public static ExpectedCondition<Boolean> pageState(final String state) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                String returnVal = jse.executeScript("return document.readyState").toString();
                return returnVal.equals(state);
            }

            @Override
            public String toString() {
                return String.format("webpage load-state to be '%s'", state);
            }
        };
    }

    public static ExpectedCondition<Boolean> urlContains(final String... substrings) {
        return new ExpectedCondition<Boolean>() {
            private String currentUrl = "";

            @Override
            public Boolean apply(WebDriver driver) {
                currentUrl = driver.getCurrentUrl();
                for (String substring : substrings) {
                    if (currentUrl != null && currentUrl.contains(substring)) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                String description0 = "url to contain any of the following substrings: %s. ";
                String description1 = "".equals(currentUrl) ? "" : "Current url is '%s'";
                StringBuilder sb = new StringBuilder();
                int substringCount = substrings.length;

                // delimit urls by commas
                for (int i = 0; i < substringCount; i++) {
                    boolean isLastElement = i == substringCount - 1;
                    String delimiter = isLastElement ? "" : ", ";
                    String substring = String.format("'%s'", substrings[i]);

                    sb.append(substring + delimiter);
                }
                String descriptionFormatted0 = String.format(description0, sb.toString());
                String descriptionFormatted1 = String.format(description1, currentUrl);
                return descriptionFormatted0 + descriptionFormatted1;
            }
        };
    }

    public static ExpectedCondition<Boolean> elementEnabledness(final By by, final boolean isEnabled) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                boolean enabledness = false;
                try {
                    enabledness = driver.findElement(by).isEnabled();
                } catch (WebDriverException wde) {
                }
                return enabledness == isEnabled;
            }

            @Override
            public String toString() {
                return "element to be " + (isEnabled ? "en" : "dis") + "abled " + by;
            }
        };
    }


    public static ExpectedCondition<Boolean> elementKeyability(final By by) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    WebElement we = driver.findElement(by);
                    we.sendKeys(" ", Keys.BACK_SPACE);
                    return true;
                } catch (WebDriverException wde) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("element to be keyable [ %s ]", by.toString());
            }
        };
    }
}