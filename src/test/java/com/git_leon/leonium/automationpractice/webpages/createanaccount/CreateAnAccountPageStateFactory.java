package com.git_leon.leonium.automationpractice.webpages.createanaccount;

import com.github.git_leon.RandomUtils;

import java.util.Arrays;

public class CreateAnAccountPageStateFactory {
    public static CreateAnAccountPageStateBuilder createRandomCreateAnAccountPageStateBuilder() {
        return new CreateAnAccountPageStateBuilder()
                .setPersonalInfoRadioButtonMrTitle(RandomUtils.createBoolean(50))
                .setPersonalInfoRadioButtonMrsTitle(RandomUtils.createBoolean(50))
                .setPersonalInfoCheckBoxSignUpForNewsLetter(RandomUtils.createBoolean(50))
                .setPersonalInfoCheckBoxReceiveSpecialOffers(RandomUtils.createBoolean(50))
                .setAddressInfoDropDownCountry("United State")
                .setAddressInfoInputAdditionalInfo(RandomUtils.createString('a', 'z', 10))
                .setAddressInfoInputAddressAlias(RandomUtils.createString('a', 'z', 5))
                .setAddressInfoInputCity("Delaware")
                .setPersonalInfoDropDownBirthDay(RandomUtils.createInteger(1, 28).toString())
                .setPersonalInfoDropDownBirthMonth(RandomUtils.selectElement("January,February,March,April,May,June,July,August,September,October,November,December".split(",")))
                .setPersonalInfoDropDownBirthYear(RandomUtils.createInteger(1900, 2020).toString())
                .setAddressInfoInputPhone(RandomUtils.createLong(1000000000L, 9999999999L).toString())
                .setAddressInfoInputMobilePhone(RandomUtils.createLong(1000000000L, 9999999999L).toString());
    }

    public static CreateAnAccountPageState createRandomCreateAnAccountPageState() {
        return createRandomCreateAnAccountPageStateBuilder()
                .build();
    }
}
