package com.git_leon.leonium.browsertools.browserhandler;

import com.github.git_leon.logging.FunctionExecutionTimeLogger;
import com.github.git_leon.logging.SimpleLoggerInterface;
import com.github.git_leon.logging.SimpleLoggerWarehouse;

/**
 * @author leonhunter
 * @created 04/29/2020 - 8:35 PM
 */
public class BrowserHandlerTimeLogger extends BrowserHandlerDecorateeAbstractClass implements BrowserHandlerLoggerInterface {
    public BrowserHandlerTimeLogger(BrowserHandlerInterface decoratee) {
        super(decoratee);
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return new FunctionExecutionTimeLogger(SimpleLoggerWarehouse.getLogger(
                getBrowserHandlerDecoratee().getDriver().toString()));
    }

    @Override
    public void finalize() {
        getBrowserHandlerDecoratee().finalize();
    }
}
