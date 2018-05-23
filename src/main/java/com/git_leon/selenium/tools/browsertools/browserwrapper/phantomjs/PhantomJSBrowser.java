package com.git_leon.selenium.tools.browsertools.browserwrapper.phantomjs;

import com.git_leon.selenium.tools.browsertools.browserwrapper.DesiredCapabilitiesFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author leon on 5/23/18.
 */
public class PhantomJSBrowser implements PhantomJSDriverInterface {
    private final PhantomJSDriver phantomJsDriver;

    public PhantomJSBrowser(PhantomJSDriver phantomJsDriver) {
        this.phantomJsDriver = phantomJsDriver;
    }

    public PhantomJSBrowser() {
        this(new PhantomJSDriver(DesiredCapabilitiesFactory.getPhantomJs()));
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) {
        return phantomJsDriver.getScreenshotAs(target);
    }

    @Override
    public Object executePhantomJS(String script, Object... args) {
        return phantomJsDriver.executePhantomJS(script, args);
    }

    @Override
    public Capabilities getCapabilities() {
        return phantomJsDriver.getCapabilities();
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        return phantomJsDriver.executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return phantomJsDriver.executeScript(s, objects);
    }

    @Override
    public void get(String s) {
        phantomJsDriver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return phantomJsDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return phantomJsDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return phantomJsDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return phantomJsDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return phantomJsDriver.getPageSource();
    }

    @Override
    public void close() {
        phantomJsDriver.close();
    }

    @Override
    public void quit() {
        phantomJsDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return phantomJsDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return phantomJsDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return phantomJsDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return phantomJsDriver.navigate();
    }

    @Override
    public Options manage() {
        return phantomJsDriver.manage();
    }

    @Override
    public Keyboard getKeyboard() {
        return phantomJsDriver.getKeyboard();
    }

    @Override
    public Mouse getMouse() {
        return phantomJsDriver.getMouse();
    }

    @Override
    public void perform(Collection<Sequence> collection) {
        phantomJsDriver.perform(collection);
    }

    @Override
    public void resetInputState() {
        phantomJsDriver.resetInputState();
    }

    @Override
    public WebElement findElementByClassName(String s) {
        return phantomJsDriver.findElementByClassName(s);
    }

    @Override
    public List<WebElement> findElementsByClassName(String s) {
        return phantomJsDriver.findElementsByClassName(s);
    }

    @Override
    public WebElement findElementByCssSelector(String s) {
        return phantomJsDriver.findElementByCssSelector(s);
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String s) {
        return phantomJsDriver.findElementsByCssSelector(s);
    }

    @Override
    public WebElement findElementById(String s) {
        return phantomJsDriver.findElementById(s);
    }

    @Override
    public List<WebElement> findElementsById(String s) {
        return phantomJsDriver.findElementsById(s);
    }

    @Override
    public WebElement findElementByLinkText(String s) {
        return phantomJsDriver.findElementByLinkText(s);
    }

    @Override
    public List<WebElement> findElementsByLinkText(String s) {
        return phantomJsDriver.findElementsByLinkText(s);
    }

    @Override
    public WebElement findElementByPartialLinkText(String s) {
        return phantomJsDriver.findElementByPartialLinkText(s);
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String s) {
        return phantomJsDriver.findElementsByPartialLinkText(s);
    }

    @Override
    public WebElement findElementByName(String s) {
        return phantomJsDriver.findElementByName(s);
    }

    @Override
    public List<WebElement> findElementsByName(String s) {
        return phantomJsDriver.findElementsByName(s);
    }

    @Override
    public WebElement findElementByTagName(String s) {
        return phantomJsDriver.findElementByTagName(s);
    }

    @Override
    public List<WebElement> findElementsByTagName(String s) {
        return phantomJsDriver.findElementsByTagName(s);
    }

    @Override
    public WebElement findElementByXPath(String s) {
        return phantomJsDriver.findElementByXPath(s);
    }

    @Override
    public List<WebElement> findElementsByXPath(String s) {
        return phantomJsDriver.findElementsByXPath(s);
    }
}
