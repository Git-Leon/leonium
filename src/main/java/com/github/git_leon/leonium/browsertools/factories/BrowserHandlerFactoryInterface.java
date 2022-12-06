package com.github.git_leon.leonium.browsertools.factories;

import com.github.git_leon.extentreporting.ExtentTestLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandler;
import com.github.git_leon.leonium.browsertools.browserhandler.core.BrowserHandlerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerImpl;
import com.github.git_leon.leonium.browsertools.browserhandler.logging.BrowserHandlerLoggerInterface;
import com.github.git_leon.leonium.browsertools.browserhandler.reporting.BrowserHandlerLayeredLogger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;
import java.util.function.Supplier;

    public interface BrowserHandlerFactoryInterface {
    Function<Capabilities, WebDriver> getWebDriverConstructor();

    Supplier<Capabilities> getCapabilitiesSupplier();

    default BrowserHandlerInterface getBrowserHandler(Capabilities capabilities) {
        return new BrowserHandler(getDriver(capabilities), 15);
    }

    default BrowserHandlerInterface getBrowserHandler() {
        return getBrowserHandler(getCapabilitiesSupplier().get());
    }

    default BrowserHandlerLoggerInterface getBrowserHandlerLogger(Capabilities capabilities) {
        return new BrowserHandlerLoggerImpl(getBrowserHandler(capabilities));
    }

    default BrowserHandlerLoggerInterface getBrowserHandlerLogger() {
        return getBrowserHandlerLogger(getCapabilitiesSupplier().get());
    }

    default BrowserHandlerLayeredLogger getBrowserHandlerLayeredLogger(ExtentTestLoggerInterface extentTestLogger) {
        return new BrowserHandlerLayeredLogger(getDriver(), extentTestLogger);
    }

    default WebDriver getDriver() {
        return getDriver(getCapabilitiesSupplier().get());
    }

    default WebDriver getDriver(Capabilities capabilities) {
        return getWebDriverConstructor().apply(this.getCapabilitiesSupplier().get().merge(capabilities));
    }
}
