package com.github.git_leon.leonium;

import java.io.File;

/**
 * @author leonhunter
 * @created 05/04/2020 - 2:34 AM
 */
public enum DirectoryReference {
    RESOURCE_DIRECTORY(new StringBuilder()
            .append(System.getProperty("user.dir")) // local directory
            .append("/src/main/resources/")
            .toString()),
    TARGET_DIRECTORY(new StringBuilder()
            .append(System.getProperty("user.dir")) // local directory
            .append("/target/")
            .toString()),
    REPORT_DIRECTORY(new StringBuilder()
            .append(System.getProperty("user.dir")) // local directory
            .append("/target/reports/")
            .toString()),
    TEST_REPORT_DIRECTORY(new StringBuilder()
            .append(System.getProperty("user.dir")) // local directory
            .append("/target/reports.test/")
            .toString());


    private final String path;

    DirectoryReference(String path) {
        this.path = path;
    }

    public String getDirectoryPath() {
        return path;
    }

    public File getDirectoryFile() {
        return new File(getDirectoryPath());
    }

    public File getFileFromDirectory(String fileName) {
        return new File(getDirectoryPath() + fileName);
    }
}

