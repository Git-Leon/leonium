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
@Deprecated
public class PhantomJSBrowser extends PhantomJSDriver {
    public PhantomJSBrowser() {
        super(DesiredCapabilitiesFactory.getPhantomJs());
    }
}
