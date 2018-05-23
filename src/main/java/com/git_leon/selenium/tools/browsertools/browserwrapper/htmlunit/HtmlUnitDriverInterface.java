package com.git_leon.selenium.tools.browsertools.browserwrapper.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.*;

import java.util.List;
import java.util.Set;

/**
 * @author leon on 5/23/18.
 */
public interface HtmlUnitDriverInterface extends WebDriver, JavascriptExecutor, FindsById, FindsByLinkText, FindsByXPath, FindsByName, FindsByCssSelector, FindsByTagName, FindsByClassName, HasCapabilities, HasInputDevices {
    BrowserVersion getBrowserVersion();

    void setProxySettings(Proxy proxy);

    void setProxy(String host, int port);

    void setHTTPProxy(String host, int port, List<String> noProxyHosts);

    void setSocksProxy(String host, int port);

    void setSocksProxy(String host, int port, List<String> noProxyHosts);

    void setAutoProxy(String autoProxyUrl);

    Capabilities getCapabilities();

    void get(String url);

    String getCurrentUrl();

    String getTitle();

    WebElement findElement(By by);

    List<WebElement> findElements(By by);

    String getPageSource();

    void close();

    void quit();

    Set<String> getWindowHandles();

    String getWindowHandle();

    Object executeScript(String script, Object... args);

    Object executeAsyncScript(String script, Object... args);

    Keyboard getKeyboard();

    Mouse getMouse();

    TargetLocator switchTo();

    Navigation navigate();

    WebElement findElementByLinkText(String selector);

    HtmlUnitWebElement getElementById(int id);

    List<WebElement> findElementsByLinkText(String selector);

    WebElement findElementById(String id);

    List<WebElement> findElementsById(String id);

    WebElement findElementByClassName(String className);

    List<WebElement> findElementsByClassName(String className);

    WebElement findElementByCssSelector(String using);

    List<WebElement> findElementsByCssSelector(String using);

    WebElement findElementByName(String name);

    List<WebElement> findElementsByName(String name);

    WebElement findElementByTagName(String name);

    List<WebElement> findElementsByTagName(String name);

    WebElement findElementByXPath(String selector);

    List<WebElement> findElementsByXPath(String selector);

    boolean isJavascriptEnabled();

    void setJavascriptEnabled(boolean enableJavascript);

    boolean isDownloadImages();

    void setDownloadImages(boolean downloadImages);

    void setAcceptSslCertificates(boolean accept);

    boolean isAcceptSslCertificates();

    Options manage();

    WebElement findElementByPartialLinkText(String using);

    List<WebElement> findElementsByPartialLinkText(String using);
}
