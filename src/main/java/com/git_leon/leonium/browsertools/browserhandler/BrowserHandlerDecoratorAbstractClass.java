package com.git_leon.leonium.browsertools.browserhandler;

import org.openqa.selenium.WebDriver;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:42 PM
 */
abstract public class BrowserHandlerDecoratorAbstractClass implements BrowserHandlerDecoratorInterface {
    private final BrowserHandlerInterface decoratee;

    public BrowserHandlerDecoratorAbstractClass(BrowserHandlerInterface decoratee) {
        this.decoratee = decoratee;
    }

    public BrowserHandlerDecoratorAbstractClass(WebDriver driver) {
        this(new BrowserHandler(driver));
    }

    @Override
    public BrowserHandlerInterface getBrowserHandlerDecoratee() {
        return decoratee;
    }

    @Override
    public void finalize() {
        decoratee.finalize();
    }
}
