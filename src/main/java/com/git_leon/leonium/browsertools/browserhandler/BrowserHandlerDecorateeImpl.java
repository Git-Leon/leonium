package com.git_leon.leonium.browsertools.browserhandler;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:42 PM
 */
public class BrowserHandlerDecorateeImpl implements BrowserHandlerDecoratorInterface {
    private final BrowserHandlerInterface decoratee;

    public BrowserHandlerDecorateeImpl(BrowserHandlerInterface decoratee) {
        this.decoratee = decoratee;
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
