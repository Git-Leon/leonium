package com.git_leon.selenium.tools.browsertools.browserwrapper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.internal.*;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.ErrorHandler;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.SessionId;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * @author leon on 5/23/18.
 */
public interface RemoteWebDriverInterface extends WebDriver, JavascriptExecutor, FindsById, FindsByClassName, FindsByLinkText, FindsByName, FindsByCssSelector, FindsByTagName, FindsByXPath, HasInputDevices, HasCapabilities, Interactive, TakesScreenshot {
    void setFileDetector(FileDetector detector);

    SessionId getSessionId();

    ErrorHandler getErrorHandler();

    void setErrorHandler(ErrorHandler handler);

    CommandExecutor getCommandExecutor();

    Capabilities getCapabilities();

    void get(String url);

    String getTitle();

    String getCurrentUrl();

    <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException;

    List<WebElement> findElements(By by);

    WebElement findElement(By by);

    WebElement findElementById(String using);

    List<WebElement> findElementsById(String using);

    WebElement findElementByLinkText(String using);

    List<WebElement> findElementsByLinkText(String using);

    WebElement findElementByPartialLinkText(String using);

    List<WebElement> findElementsByPartialLinkText(String using);

    WebElement findElementByTagName(String using);

    List<WebElement> findElementsByTagName(String using);

    WebElement findElementByName(String using);

    List<WebElement> findElementsByName(String using);

    WebElement findElementByClassName(String using);

    List<WebElement> findElementsByClassName(String using);

    WebElement findElementByCssSelector(String using);

    List<WebElement> findElementsByCssSelector(String using);

    WebElement findElementByXPath(String using);

    List<WebElement> findElementsByXPath(String using);

    String getPageSource();

    void close();

    void quit();

    Set<String> getWindowHandles();

    String getWindowHandle();

    Object executeScript(String script, Object... args);

    Object executeAsyncScript(String script, Object... args);

    TargetLocator switchTo();

    Navigation navigate();

    Options manage();

    void setLogLevel(Level level);

    void perform(Collection<Sequence> actions);

    void resetInputState();

    Keyboard getKeyboard();

    Mouse getMouse();

    FileDetector getFileDetector();

    String toString();

    public static enum When {
        BEFORE,
        AFTER,
        EXCEPTION;

        private When() {
        }
    }
}
