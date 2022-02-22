package com.github.git_leon.leonium.extentreporting;

import com.github.git_leon.leonium.DirectoryReference;
import com.github.git_leon.stringutils.StringUtils;

import java.io.File;
import java.time.LocalDateTime;

public enum ExtentTestLoggerFactoryManager {
    RESOURCE_DIRECTORY(DirectoryReference.RESOURCE_DIRECTORY),
    TARGET_DIRECTORY(DirectoryReference.TARGET_DIRECTORY),
    REPORT_DIRECTORY(DirectoryReference.REPORT_DIRECTORY),
    TEST_REPORT_DIRECTORY(DirectoryReference.TEST_REPORT_DIRECTORY);

    private final File directoryFile;
    private ExtentTestLoggerFactory extentTestLoggerFactory;

    ExtentTestLoggerFactoryManager(DirectoryReference directoryReference) {
        this(directoryReference
                .getFileFromDirectory("reports/"
                        .concat(StringUtils.removeCharacters(LocalDateTime.now().toString(), ":_"))
                        .concat("/index.html")));
    }

    ExtentTestLoggerFactoryManager(File directoryFile) {
        this.directoryFile = directoryFile;
    }

    public File getReportFilePath() {
        return directoryFile;
    }

    public ExtentTestLoggerFactory getExtentTestLoggerFactory() {
        if (extentTestLoggerFactory == null) {
            this.extentTestLoggerFactory = new ExtentTestLoggerFactory(getReportFilePath().getAbsolutePath());
        }
        return extentTestLoggerFactory;
    }
}
