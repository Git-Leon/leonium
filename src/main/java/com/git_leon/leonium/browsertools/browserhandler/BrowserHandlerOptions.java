package com.git_leon.leonium.browsertools.browserhandler;

/**
 * Created by leon on 5/25/17.
 */

public final class BrowserHandlerOptions {
    public final BrowserOption<Boolean> SCREENSHOT_ON_EVENT = screenshotOnEvent();
    public final BrowserOption<Boolean> SCREENSHOT_ON_CLICK = new BrowserOption<>(false);
    public final BrowserOption<Boolean> SCREENSHOT_ON_SELECT = new BrowserOption<>(false);
    public final BrowserOption<Boolean> SCREENSHOT_ON_SENDKEYS = new BrowserOption<>(false);
    public final BrowserOption<Integer> DEFAULT_WAIT = new BrowserOption<>(15);
    public final BrowserOption<String> SCREENSHOT_DIRECTORY = new BrowserOption<>(new StringBuilder()
        .append(System.getProperty("user.dir"))
        .append("/target/")
        .toString());


    public class BrowserOption<T> {
        protected T value;

        public BrowserOption() {
        }

        public BrowserOption(T value) {
            this.value = value;
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
                SCREENSHOT_ON_CLICK.setValue(value);
                SCREENSHOT_ON_SENDKEYS.setValue(value);
                SCREENSHOT_ON_SELECT.setValue(value);
            }
        };
    }
}
