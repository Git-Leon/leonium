package com.git_leon.selenium.smartystreet.pages;

import com.git_leon.selenium.tools.browsertools.WebCrawl;
import com.git_leon.selenium.tools.browsertools.WebPage;
import org.openqa.selenium.By;

public class DemoPage extends WebPage {
    public final By byServiceUS = By.cssSelector("label[for='service-us']");
    public final By byServiceIntl = By.cssSelector("label[for='service-international']");

    public final static class USView {
        public final static By byButtonSubmit = By.id("submit-components");
        public final static By bySelectLookup = By.id("input-selector-text");
        public final static By bySelectSample = By.id("sample-selector-text");
        public final static By byInputStreet1 = By.id("address-street");
        public final static By byInputStreet2 = By.id("address-street2");
        public final static By byInputCity = By.id("address-city");
        public final static By byInputState = By.id("address-state");
        public final static By byInputZipcode = By.id("address-zipcode");
        public final static By bySpanMatchCount = By.className("match-count");
    }

    public final static class InternationalView {
        public final static By byButtonSubmit = By.id("submit-components-intl");
        public final static By byInputCountry = By.id("address-country-intl");
        public final static By byInputStreet1 = By.id("#address-address1-intl");
        public final static By byInputStreet2 = By.id("#address-address2-intl");
        public final static By byInputCity = By.id("#address-locality-intl");
        public final static By byInputProvince = By.id("##address-administrative-area-intl");
        public final static By byInputPostalCode = By.id("#address-postal-code-intl");
        public final static By bySelectSample = By.id("sample-selector-text-international");
        public final static By bySelectLookup = By.id("input-selector-text-international");
    }

    public DemoPage(WebCrawl runner) {
        super(runner);
    }

    public void selectService(String service) {
        if (service.toLowerCase().equals("us")) {
            browserHandler.click(byServiceUS);
        } else {
            browserHandler.click(byServiceIntl);
        }
    }

    public void enterDetails(String street1, String city, String state, String zipcode) {
        enterDetails(street1, null, city, state, zipcode);
    }

    public void enterDetails(String street1, String street2, String city, String state, String zipcode) {
        browserHandler.sendKeys(USView.byInputStreet1, street1);
        browserHandler.sendKeys(USView.byInputStreet1, street2);
        browserHandler.sendKeys(USView.byInputCity, city);
        browserHandler.sendKeys(USView.byInputState, state);
        browserHandler.sendKeys(USView.byInputZipcode, zipcode);
    }

    public void enterDetailsIntl(String country, String street1, String city, String province, String postalCode) {
        enterDetailsIntl(country, street1, null, city, province, postalCode);
    }

    public void enterDetailsIntl(String country, String street1, String street2, String city, String province,
                                 String postalCode) {
        browserHandler.sendKeys(InternationalView.byInputCountry, country);
        browserHandler.sendKeys(InternationalView.byInputStreet1, street1);
        browserHandler.sendKeys(InternationalView.byInputStreet1, street2);
        browserHandler.sendKeys(InternationalView.byInputCity, city);
        browserHandler.sendKeys(InternationalView.byInputProvince, province);
        browserHandler.sendKeys(InternationalView.byInputPostalCode, postalCode);
    }

}