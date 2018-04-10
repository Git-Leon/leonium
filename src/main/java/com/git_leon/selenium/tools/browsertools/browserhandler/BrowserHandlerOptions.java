package com.git_leon.selenium.tools.browsertools.browserhandler;

/**
 * Created by leon on 5/25/17.
 */

public final class BrowserHandlerOptions {
    public final BrowserOption<Boolean> screenshotOnEvent = screenshotOnEvent();
    public final BrowserOption<Boolean> screenshotOnClick = new BrowserOption<>();
    public final BrowserOption<Boolean> screenshotOnSelect = new BrowserOption<>();
    public final BrowserOption<Boolean> screenshotOnSendKeys = new BrowserOption<>();

    public final BrowserOption<Boolean> continueOnNoSuchElement = new BrowserOption<>();
    public final BrowserOption<Boolean> continueOnTimeout = new BrowserOption<>();
    public final BrowserOption<Integer> defaultWait = new BrowserOption<>(15);
    public final BrowserOption<Boolean> logOnWait = new BrowserOption<>();
    public BrowserOption<Boolean> logOnScreenshot = new BrowserOption<>();

    public class BrowserOption<T> {
        protected T value;

        public BrowserOption() {
        }

        public BrowserOption(T value) {
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }
    }

    private final BrowserOption<Boolean> screenshotOnEvent() {
        return new BrowserOption<Boolean>() {
            @Override
            public void setValue(Boolean value) {
                this.value = value;
                screenshotOnClick.setValue(value);
                screenshotOnSendKeys.setValue(value);
                screenshotOnSelect.setValue(value);
            }
        };
    }
}