package com.git_leon.leonium.automationpractice.webpages.createanaccount;

import com.git_leon.leonium.browsertools.WebPage;
import com.git_leon.leonium.browsertools.browserhandler.WebEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAnAccountPage extends WebPage {
    private By personalInfoRadioButtonMrTitle = By.id("uniform-id_gender1");
    private By personalInfoRadioButtonMrsTitle= By.id("uniform-id_gender2");
    private By personalInfoInputFirstName = By.id("customer_firstname");
    private By personalInfoInputLastName = By.id("customer_lastname");
    private By personalInfoEmail = By.id("email");
    private By personalInfoPassword = By.id("passwd");
    private By personalInfoDropDownBirthMonth = By.id("months");
    private By personalInfoDropDownBirthDay = By.id("days");
    private By personalInfoDropDownBirthYear = By.id("years");
    private By personalInfoCheckBoxSignUpForNewsLetter = By.id("newsletter");
    private By personalInfoCheckBoxReceiveSpecialOffers = By.id("optin");
    private By addressInfoInputFirstName = By.id("firstname");
    private By addressInfoInputLastName = By.id("lastname");
    private By addressInfoInputCompany = By.id("company");
    private By addressInfoInputLine1 = By.id("address1");
    private By addressInfoInputLine2 = By.id("address2");
    private By addressInfoInputCity = By.id("city");
    private By addressInfoInputZipcode = By.id("postcode");
    private By addressInfoInputAdditionalInfo = By.id("other");
    private By addressInfoInputPhone = By.id("phone");
    private By addressInfoInputMobilePhone = By.id("phone_mobile");
    private By addressInfoInputAddressAlias = By.id("alias");
    private By addressInfoDropDownState = By.id("id_state");
    private By addressInfoDropDownCountry = By.id("id_country");
    private By buttonSubmitAccount = By.id("submitAccount");


    public CreateAnAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void navigateTo() {
        getBrowserHandler().navigateTo("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
    }
}
