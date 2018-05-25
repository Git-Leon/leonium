package com.git_leon.selenium.smartystreet.crawlpaths;

import com.git_leon.selenium.smartystreet.pages.DemoPage;
import com.git_leon.selenium.tools.mockdata.Actor;
import com.git_leon.selenium.tools.mockdata.ActorFactory;
import org.junit.Test;

/**
 * Created by leon on 8/17/17.
 */
public class SmartyStreetWebCrawl extends AbstractSmartyStreetWebCrawl {

    @Override
    public void setup() {
        browserHandler.options.SCREENSHOT_ON_EVENT.setValue(false);
        browserHandler.options.DEFAULT_WAIT.setValue(60);
    }

    @Test
    @Override
    public void test() {
        Actor actor = ActorFactory.createDefaultActor();
        browserHandler.options.SCREENSHOT_ON_EVENT.setValue(true);
        browserHandler.navigateTo("https://smartystreets.com/");
        demoPage.selectService("us");
        demoPage.enterDetails(actor.getAddressLine1(), actor.getCity(), actor.getState(), actor.getZipcode());
        browserHandler.click(DemoPage.USView.byButtonSubmit);
        browserHandler.options.DEFAULT_WAIT.setValue(60);
        browserHandler.wait.forPageLoad();
    }
}