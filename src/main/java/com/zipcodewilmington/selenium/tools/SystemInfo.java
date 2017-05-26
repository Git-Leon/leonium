package com.zipcodewilmington.selenium.tools;

/**
 * Created by leon on 5/25/17.
 */
public interface SystemInfo {
    String currentProjectPath = System.getProperty("user.dir");
    String artifactFolder = String.format("%s/target/artifacts", currentProjectPath);
}
