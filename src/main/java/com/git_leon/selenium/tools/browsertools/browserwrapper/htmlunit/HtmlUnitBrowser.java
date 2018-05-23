package com.git_leon.selenium.tools.browsertools.browserwrapper.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.git_leon.selenium.tools.browsertools.browserwrapper.DesiredCapabilitiesFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.Set;

/**
 * @author leon on 5/23/18.
 */
public class HtmlUnitBrowser implements HtmlUnitDriverInterface {
    private final HtmlUnitDriver htmlUnitDriver;

    public HtmlUnitBrowser(HtmlUnitDriver htmlUnitDriver) {
        this.htmlUnitDriver = htmlUnitDriver;
    }

    public HtmlUnitBrowser(DesiredCapabilities desiredCapabilities) {
        this(new HtmlUnitDriver(desiredCapabilities));
    }

    public HtmlUnitBrowser() {
        this(DesiredCapabilitiesFactory.getHtmlUnit());
    }

    @Override
    public BrowserVersion getBrowserVersion() {
        return htmlUnitDriver.getBrowserVersion();
    }

    @Override
    public void setProxySettings(Proxy proxy) {
        htmlUnitDriver.setProxySettings(proxy);
    }

    @Override
    public void setProxy(String host, int port) {
        htmlUnitDriver.setProxy(host, port);
    }

    @Override
    public void setHTTPProxy(String host, int port, List<String> noProxyHosts) {
        htmlUnitDriver.setHTTPProxy(host, port, noProxyHosts);
    }

    @Override
    public void setSocksProxy(String host, int port) {
        htmlUnitDriver.setSocksProxy(host, port);
    }

    @Override
    public void setSocksProxy(String host, int port, List<String> noProxyHosts) {
        htmlUnitDriver.setSocksProxy(host, port, noProxyHosts);
    }

    @Override
    public void setAutoProxy(String autoProxyUrl) {
        htmlUnitDriver.setAutoProxy(autoProxyUrl);
    }

    @Override
    public Capabilities getCapabilities() {
        return htmlUnitDriver.getCapabilities();
    }

    @Override
    public void get(String url) {
        htmlUnitDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return htmlUnitDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return htmlUnitDriver.getTitle();
    }

    @Override
    public WebElement findElement(By by) {
        return htmlUnitDriver.findElement(by);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return htmlUnitDriver.findElements(by);
    }

    @Override
    public String getPageSource() {
        return htmlUnitDriver.getPageSource();
    }

    @Override
    public void close() {
        htmlUnitDriver.close();
    }

    @Override
    public void quit() {
        htmlUnitDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return htmlUnitDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return htmlUnitDriver.getWindowHandle();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return htmlUnitDriver.executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return htmlUnitDriver.executeAsyncScript(script, args);
    }

    @Override
    public Keyboard getKeyboard() {
        return htmlUnitDriver.getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return htmlUnitDriver.getMouse();
    }

    @Override
    public TargetLocator switchTo() {
        return htmlUnitDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return htmlUnitDriver.navigate();
    }

    @Override
    public WebElement findElementByLinkText(String selector) {
        return htmlUnitDriver.findElementByLinkText(selector);
    }

    @Override
    public HtmlUnitWebElement getElementById(int id) {
        return htmlUnitDriver.getElementById(id);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String selector) {
        return htmlUnitDriver.findElementsByLinkText(selector);
    }

    @Override
    public WebElement findElementById(String id) {
        return htmlUnitDriver.findElementById(id);
    }

    @Override
    public List<WebElement> findElementsById(String id) {
        return htmlUnitDriver.findElementsById(id);
    }

    @Override
    public WebElement findElementByClassName(String className) {
        return htmlUnitDriver.findElementByClassName(className);
    }

    @Override
    public List<WebElement> findElementsByClassName(String className) {
        return htmlUnitDriver.findElementsByClassName(className);
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return htmlUnitDriver.findElementByCssSelector(using);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return htmlUnitDriver.findElementsByCssSelector(using);
    }

    @Override
    public WebElement findElementByName(String name) {
        return htmlUnitDriver.findElementByName(name);
    }

    @Override
    public List<WebElement> findElementsByName(String name) {
        return htmlUnitDriver.findElementsByName(name);
    }

    @Override
    public WebElement findElementByTagName(String name) {
        return htmlUnitDriver.findElementByTagName(name);
    }

    @Override
    public List<WebElement> findElementsByTagName(String name) {
        return htmlUnitDriver.findElementsByTagName(name);
    }

    @Override
    public WebElement findElementByXPath(String selector) {
        return htmlUnitDriver.findElementByXPath(selector);
    }

    @Override
    public List<WebElement> findElementsByXPath(String selector) {
        return htmlUnitDriver.findElementsByXPath(selector);
    }

    @Override
    public boolean isJavascriptEnabled() {
        return htmlUnitDriver.isJavascriptEnabled();
    }

    @Override
    public void setJavascriptEnabled(boolean enableJavascript) {
        htmlUnitDriver.setJavascriptEnabled(enableJavascript);
    }

    @Override
    public boolean isDownloadImages() {
        return htmlUnitDriver.isDownloadImages();
    }

    @Override
    public void setDownloadImages(boolean downloadImages) {
        htmlUnitDriver.setDownloadImages(downloadImages);
    }

    @Override
    public void setAcceptSslCertificates(boolean accept) {
        htmlUnitDriver.setAcceptSslCertificates(accept);
    }

    @Override
    public boolean isAcceptSslCertificates() {
        return htmlUnitDriver.isAcceptSslCertificates();
    }

    @Override
    public Options manage() {
        return htmlUnitDriver.manage();
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return htmlUnitDriver.findElementByPartialLinkText(using);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return htmlUnitDriver.findElementsByPartialLinkText(using);
    }
}
