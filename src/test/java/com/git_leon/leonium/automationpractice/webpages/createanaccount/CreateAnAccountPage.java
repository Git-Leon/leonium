package com.git_leon.leonium.automationpractice.webpages.createanaccount;

import com.git_leon.leonium.browsertools.WebPage;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandler;
import com.git_leon.leonium.browsertools.browserhandler.BrowserHandlerInterface;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateAnAccountPage extends WebPage {
    private CreateAnAccountPageState pageState;
    private By byPersonalInfoRadioButtonMrTitle = By.id("uniform-id_gender1");
    private By byPersonalInfoRadioButtonMrsTitle = By.id("uniform-id_gender2");
    private By byPersonalInfoInputFirstName = By.id("customer_firstname");
    private By byPersonalInfoInputLastName = By.id("customer_lastname");
    private By byPersonalInfoEmail = By.id("email");
    private By byPersonalInfoPassword = By.id("passwd");
    private By byPersonalInfoDropDownBirthMonth = By.cssSelector("#months");//By.id("months");
    private By byPersonalInfoDropDownBirthDay = By.cssSelector("#days");//By.id("days");
    private By byPersonalInfoDropDownBirthYear = By.cssSelector("#years");//By.id("years");
    private By byPersonalInfoCheckBoxSignUpForNewsLetter = By.id("newsletter");
    private By byPersonalInfoCheckBoxReceiveSpecialOffers = By.id("optin");
    private By byAddressInfoInputFirstName = By.id("firstname");
    private By byAddressInfoInputLastName = By.id("lastname");
    private By byAddressInfoInputCompany = By.id("company");
    private By byAddressInfoInputLine1 = By.id("address1");
    private By byAddressInfoInputLine2 = By.id("address2");
    private By byAddressInfoInputCity = By.id("city");
    private By byAddressInfoInputZipcode = By.id("postcode");
    private By byAddressInfoInputAdditionalInfo = By.id("other");
    private By byAddressInfoInputPhone = By.id("phone");
    private By byAddressInfoInputMobilePhone = By.id("phone_mobile");
    private By byAddressInfoInputAddressAlias = By.id("alias");
    private By byAddressInfoDropDownState = By.id("id_state");
    private By byAddressInfoDropDownCountry = By.id("id_country");
    private By byButtonSubmitAccount = By.id("submitAccount");


    public CreateAnAccountPage(BrowserHandlerInterface browserHandler) {
        super(browserHandler);
    }

    @Override
    public void navigateTo() {
        getBrowserHandler().navigateTo("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation");
    }

    public void inputData() {
        inputPersonalInfoInputFirstName(getPageState().getPersonalInfoInputFirstName());
        inputPersonalInfoInputLastName(getPageState().getPersonalInfoInputLastName());
        inputPersonalInfoEmail(getPageState().getPersonalInfoEmail());
        inputPersonalInfoPassword(getPageState().getPersonalInfoPassword());
        inputAddressInfoInputFirstName(getPageState().getAddressInfoInputFirstName());
        inputAddressInfoInputLastName(getPageState().getAddressInfoInputLastName());
        inputAddressInfoInputCompany(getPageState().getAddressInfoInputCompany());
        inputAddressInfoInputLine1(getPageState().getAddressInfoInputLine1());
        inputAddressInfoInputLine2(getPageState().getAddressInfoInputLine2());
        inputAddressInfoInputCity(getPageState().getAddressInfoInputCity());
        inputAddressInfoInputZipcode(getPageState().getAddressInfoInputZipcode());
        inputAddressInfoInputAdditionalInfo(getPageState().getAddressInfoInputAdditionalInfo());
        inputAddressInfoInputPhone(getPageState().getAddressInfoInputPhone());
        inputAddressInfoInputMobilePhone(getPageState().getAddressInfoInputMobilePhone());
        inputAddressInfoInputAddressAlias(getPageState().getAddressInfoInputAddressAlias());
        inputAddressInfoDropDownState(getPageState().getAddressInfoDropDownState());
        inputAddressInfoDropDownCountry(getPageState().getAddressInfoDropDownCountry());
        inputPersonalInfoRadioButtonMrTitle(getPageState().getPersonalInfoRadioButtonMrTitle());
        inputPersonalInfoRadioButtonMrsTitle(getPageState().getPersonalInfoRadioButtonMrsTitle());
        inputPersonalInfoDropDownBirthMonth(getPageState().getPersonalInfoDropDownBirthMonth());
        inputPersonalInfoDropDownBirthDay(getPageState().getPersonalInfoDropDownBirthDay());
        inputPersonalInfoDropDownBirthYear(getPageState().getPersonalInfoDropDownBirthYear());
        inputPersonalInfoCheckBoxSignUpForNewsLetter(getPageState().getPersonalInfoCheckBoxSignUpForNewsLetter());
        inputPersonalInfoCheckBoxReceiveSpecialOffers(getPageState().getPersonalInfoCheckBoxReceiveSpecialOffers());
        clickButtonSubmitAccount();
    }

    public void setPageState(CreateAnAccountPageState pageState) {
        this.pageState = pageState;
    }

    public CreateAnAccountPageState getPageState() {
        return pageState;
    }

    public void inputPersonalInfoRadioButtonMrTitle(Boolean selectButton) {
        if (selectButton) {
            getBrowserHandler().click(byPersonalInfoRadioButtonMrTitle);
        }
    }

    public void inputPersonalInfoRadioButtonMrsTitle(Boolean selectButton) {
        if (selectButton) {
            getBrowserHandler().click(byPersonalInfoRadioButtonMrsTitle);
        }
    }

    public void inputPersonalInfoInputFirstName(String personalInfoInputFirstName) {
        getBrowserHandler().sendKeys(byPersonalInfoInputFirstName, personalInfoInputFirstName);
    }

    public void inputPersonalInfoInputLastName(String personalInfoInputLastName) {
        getBrowserHandler().sendKeys(byPersonalInfoInputLastName, personalInfoInputLastName);
    }

    public void inputPersonalInfoEmail(String personalInfoEmail) {
        getBrowserHandler().sendKeys(byPersonalInfoEmail, personalInfoEmail);
    }

    public void inputPersonalInfoPassword(String personalInfoPassword) {
        getBrowserHandler().sendKeys(byPersonalInfoPassword, personalInfoPassword);
    }

    public void inputPersonalInfoDropDownBirthMonth(String personalInfoDropDownBirthMonth) {
        try {
            getBrowserHandler().sendKeys(byPersonalInfoDropDownBirthMonth, personalInfoDropDownBirthMonth);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void inputPersonalInfoDropDownBirthDay(String personalInfoDropDownBirthDay) {
        try {
            getBrowserHandler().sendKeys(byPersonalInfoDropDownBirthDay, personalInfoDropDownBirthDay);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void inputPersonalInfoDropDownBirthYear(String personalInfoDropDownBirthYear) {
        try {
            getBrowserHandler().sendKeys(byPersonalInfoDropDownBirthYear, personalInfoDropDownBirthYear);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void inputPersonalInfoCheckBoxSignUpForNewsLetter(Boolean selectButton) {
        if (selectButton) {
            getBrowserHandler().select(byPersonalInfoCheckBoxSignUpForNewsLetter);
        }
    }

    public void inputPersonalInfoCheckBoxReceiveSpecialOffers(Boolean selectButton) {
        if (selectButton) {
            getBrowserHandler().select(byPersonalInfoCheckBoxReceiveSpecialOffers);
        }
    }

    public void inputAddressInfoInputFirstName(String addressInfoInputFirstName) {
        getBrowserHandler().sendKeys(byAddressInfoInputFirstName, addressInfoInputFirstName);
    }

    public void inputAddressInfoInputLastName(String addressInfoInputLastName) {
        getBrowserHandler().sendKeys(byAddressInfoInputLastName, addressInfoInputLastName);
    }

    public void inputAddressInfoInputCompany(String addressInfoInputCompany) {
        getBrowserHandler().sendKeys(byAddressInfoInputCity, addressInfoInputCompany);
    }

    public void inputAddressInfoInputLine1(String addressInfoInputLine1) {
        getBrowserHandler().sendKeys(byAddressInfoInputLine1, addressInfoInputLine1);
    }

    public void inputAddressInfoInputLine2(String addressInfoInputLine2) {
        getBrowserHandler().sendKeys(byAddressInfoInputLine2, addressInfoInputLine2);
    }

    public void inputAddressInfoInputCity(String addressInfoInputCity) {
        getBrowserHandler().sendKeys(byAddressInfoInputCity, addressInfoInputCity);
    }

    public void inputAddressInfoInputZipcode(String addressInfoInputZipcode) {
        getBrowserHandler().sendKeys(byAddressInfoInputZipcode, addressInfoInputZipcode);
    }

    public void inputAddressInfoInputAdditionalInfo(String addressInfoInputAdditionalInfo) {
        getBrowserHandler().sendKeys(byAddressInfoInputAdditionalInfo, addressInfoInputAdditionalInfo);
    }

    public void inputAddressInfoInputPhone(String addressInfoInputPhone) {
        getBrowserHandler().sendKeys(byAddressInfoInputPhone, addressInfoInputPhone);
    }

    public void inputAddressInfoInputMobilePhone(String addressInfoInputMobilePhone) {
        getBrowserHandler().sendKeys(byAddressInfoInputMobilePhone, addressInfoInputMobilePhone);
    }

    public void inputAddressInfoInputAddressAlias(String addressInfoInputAddressAlias) {
        getBrowserHandler().sendKeys(byAddressInfoInputAddressAlias, addressInfoInputAddressAlias);
    }

    public void inputAddressInfoDropDownState(String addressInfoDropDownState) {
        getBrowserHandler().sendKeys(byAddressInfoDropDownState, addressInfoDropDownState);
    }

    public void inputAddressInfoDropDownCountry(String addressInfoDropDownCountry) {
        getBrowserHandler().sendKeys(byAddressInfoDropDownCountry, addressInfoDropDownCountry);
    }

    public void clickButtonSubmitAccount() {
        getBrowserHandler().click(byButtonSubmitAccount);
    }

}
