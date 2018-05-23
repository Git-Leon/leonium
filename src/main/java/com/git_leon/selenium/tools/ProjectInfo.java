package com.git_leon.selenium.tools;

/**
 * Created by leon on 5/25/17.
 */
public interface ProjectInfo {
    String CURRENT_PROJECT_PATH = System.getProperty("user.dir");
    String ARTIFACT_FOLDER = String.format("%s/target/artifacts", CURRENT_PROJECT_PATH);
    String GECKO_DRIVER_PATH = CURRENT_PROJECT_PATH + "/src/main/resources/geckodriver";
    String CHROME_DRIVER_PATH = CURRENT_PROJECT_PATH + "/src/main/resources/chromedriver";
    String PHANTOM_DRIVER_PATH = CURRENT_PROJECT_PATH + "/src/main/resources/phantomdriver";
    String USER_AGENT = "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1";
}
