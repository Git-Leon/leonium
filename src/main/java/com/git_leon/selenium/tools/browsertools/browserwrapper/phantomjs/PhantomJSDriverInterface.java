package com.git_leon.selenium.tools.browsertools.browserwrapper.phantomjs;

/**
 * @author leon on 5/23/18.
 */

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.internal.*;

/**
 * @author leon on 5/23/18.
 */
public interface PhantomJSDriverInterface extends WebDriver, JavascriptExecutor, FindsById, FindsByClassName, FindsByLinkText, FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath, HasInputDevices, HasCapabilities, Interactive, TakesScreenshot {
    @Override
    <X> X getScreenshotAs(OutputType<X> target);

    Object executePhantomJS(String script, Object... args);
}

