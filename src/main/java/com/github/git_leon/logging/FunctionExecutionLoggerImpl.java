package com.github.git_leon.logging;

/**
 * @author leonhunter
 * created 05/04/2020 - 8:00 PM
 */
public class FunctionExecutionLoggerImpl implements FunctionExecutionLoggerInterface {
    private final SimpleLoggerInterface logger;

    public FunctionExecutionLoggerImpl(SimpleLoggerInterface logger) {
        this.logger = logger;
    }

    @Override
    public SimpleLoggerInterface getLogger() {
        return logger;
    }
}
//    cd - ; mvn package ; cd - ; package_cloud push git-leon/utils simplelogger-2.1.1.jar --coordinates=com.github.git-leon:simplelogger:2.1.1;