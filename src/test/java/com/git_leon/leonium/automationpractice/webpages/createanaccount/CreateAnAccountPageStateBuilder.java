package com.git_leon.leonium.automationpractice.webpages.createanaccount;

public class CreateAnAccountPageStateBuilder {
    private String personalInfoRadioButtonMrTitle;
    private String personalInfoRadioButtonMrsTitle;
    private String personalInfoInputFirstName;
    private String personalInfoInputLastName;
    private String personalInfoEmail;
    private String personalInfoPassword;
    private String personalInfoDropDownBirthMonth;
    private String personalInfoDropDownBirthDay;
    private String personalInfoDropDownBirthYear;
    private String personalInfoCheckBoxSignUpForNewsLetter;
    private String personalInfoCheckBoxReceiveSpecialOffers;
    private String addressInfoInputFirstName;
    private String addressInfoInputLastName;
    private String addressInfoInputCompany;
    private String addressInfoInputLine1;
    private String addressInfoInputLine2;
    private String addressInfoInputCity;
    private String addressInfoInputZipcode;
    private String addressInfoInputAdditionalInfo;
    private String addressInfoInputPhone;
    private String addressInfoInputMobilePhone;
    private String addressInfoInputAddressAlias;
    private String addressInfoDropDownState;
    private String addressInfoDropDownCountry;
    private String buttonSubmitAccount;

    public CreateAnAccountPageStateBuilder setPersonalInfoRadioButtonMrTitle(String personalInfoRadioButtonMrTitle) {
        this.personalInfoRadioButtonMrTitle = personalInfoRadioButtonMrTitle;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoRadioButtonMrsTitle(String personalInfoRadioButtonMrsTitle) {
        this.personalInfoRadioButtonMrsTitle = personalInfoRadioButtonMrsTitle;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoInputFirstName(String personalInfoInputFirstName) {
        this.personalInfoInputFirstName = personalInfoInputFirstName;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoInputLastName(String personalInfoInputLastName) {
        this.personalInfoInputLastName = personalInfoInputLastName;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoEmail(String personalInfoEmail) {
        this.personalInfoEmail = personalInfoEmail;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoPassword(String personalInfoPassword) {
        this.personalInfoPassword = personalInfoPassword;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoDropDownBirthMonth(String personalInfoDropDownBirthMonth) {
        this.personalInfoDropDownBirthMonth = personalInfoDropDownBirthMonth;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoDropDownBirthDay(String personalInfoDropDownBirthDay) {
        this.personalInfoDropDownBirthDay = personalInfoDropDownBirthDay;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoDropDownBirthYear(String personalInfoDropDownBirthYear) {
        this.personalInfoDropDownBirthYear = personalInfoDropDownBirthYear;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoCheckBoxSignUpForNewsLetter(String personalInfoCheckBoxSignUpForNewsLetter) {
        this.personalInfoCheckBoxSignUpForNewsLetter = personalInfoCheckBoxSignUpForNewsLetter;
        return this;
    }

    public CreateAnAccountPageStateBuilder setPersonalInfoCheckBoxReceiveSpecialOffers(String personalInfoCheckBoxReceiveSpecialOffers) {
        this.personalInfoCheckBoxReceiveSpecialOffers = personalInfoCheckBoxReceiveSpecialOffers;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputFirstName(String addressInfoInputFirstName) {
        this.addressInfoInputFirstName = addressInfoInputFirstName;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputLastName(String addressInfoInputLastName) {
        this.addressInfoInputLastName = addressInfoInputLastName;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputCompany(String addressInfoInputCompany) {
        this.addressInfoInputCompany = addressInfoInputCompany;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputLine1(String addressInfoInputLine1) {
        this.addressInfoInputLine1 = addressInfoInputLine1;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputLine2(String addressInfoInputLine2) {
        this.addressInfoInputLine2 = addressInfoInputLine2;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputCity(String addressInfoInputCity) {
        this.addressInfoInputCity = addressInfoInputCity;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputZipcode(String addressInfoInputZipcode) {
        this.addressInfoInputZipcode = addressInfoInputZipcode;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputAdditionalInfo(String addressInfoInputAdditionalInfo) {
        this.addressInfoInputAdditionalInfo = addressInfoInputAdditionalInfo;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputPhone(String addressInfoInputPhone) {
        this.addressInfoInputPhone = addressInfoInputPhone;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputMobilePhone(String addressInfoInputMobilePhone) {
        this.addressInfoInputMobilePhone = addressInfoInputMobilePhone;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoInputAddressAlias(String addressInfoInputAddressAlias) {
        this.addressInfoInputAddressAlias = addressInfoInputAddressAlias;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoDropDownState(String addressInfoDropDownState) {
        this.addressInfoDropDownState = addressInfoDropDownState;
        return this;
    }

    public CreateAnAccountPageStateBuilder setAddressInfoDropDownCountry(String addressInfoDropDownCountry) {
        this.addressInfoDropDownCountry = addressInfoDropDownCountry;
        return this;
    }

    public CreateAnAccountPageStateBuilder setButtonSubmitAccount(String buttonSubmitAccount) {
        this.buttonSubmitAccount = buttonSubmitAccount;
        return this;
    }

    public CreateAnAccountPageState createCreateAnAccountPageState() {
        return new CreateAnAccountPageState(personalInfoRadioButtonMrTitle, personalInfoRadioButtonMrsTitle, personalInfoInputFirstName, personalInfoInputLastName, personalInfoEmail, personalInfoPassword, personalInfoDropDownBirthMonth, personalInfoDropDownBirthDay, personalInfoDropDownBirthYear, personalInfoCheckBoxSignUpForNewsLetter, personalInfoCheckBoxReceiveSpecialOffers, addressInfoInputFirstName, addressInfoInputLastName, addressInfoInputCompany, addressInfoInputLine1, addressInfoInputLine2, addressInfoInputCity, addressInfoInputZipcode, addressInfoInputAdditionalInfo, addressInfoInputPhone, addressInfoInputMobilePhone, addressInfoInputAddressAlias, addressInfoDropDownState, addressInfoDropDownCountry, buttonSubmitAccount);
    }
}