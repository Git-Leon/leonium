package com.zipcodewilmington.selenium.tools.browsertools.browserwrapper;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class FirefoxBrowser extends FirefoxDriver {
    public FirefoxBrowser(DesiredCapabilities caps) {
        super(caps);
        this.setLogLevel(Level.OFF);
        getErrorHandler().setIncludeServerErrors(false);
    }

    public FirefoxBrowser() {
        this(BrowserUtilities.getLoglessFirefoxCapabilities());
    }

    private static FirefoxProfile defaultProfile() {
        return createProfile(false, false, false, false);
    }

    private static FirefoxProfile createProfile(boolean acceptUntrustedSsl, boolean loadNoFocusLib,
                                                boolean untrustedIssuer, boolean enableNativeEvents) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(acceptUntrustedSsl);
        profile.setAlwaysLoadNoFocusLib(loadNoFocusLib);
        profile.setAssumeUntrustedCertificateIssuer(untrustedIssuer);
        profile.setEnableNativeEvents(enableNativeEvents);
        return profile;
    }
}