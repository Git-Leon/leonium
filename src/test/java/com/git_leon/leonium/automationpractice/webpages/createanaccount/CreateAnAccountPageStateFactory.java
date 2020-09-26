package com.git_leon.leonium.automationpractice.webpages.createanaccount;

import com.github.git_leon.RandomUtils;

public class CreateAnAccountPageStateFactory {
    public static CreateAnAccountPageStateBuilder createRandomCreateAnAccountPageStateBuilder() {
        return new CreateAnAccountPageStateBuilder()
                .setAddressInfoDropDownCountry("United State")
                .setAddressInfoInputAdditionalInfo(RandomUtils.createString('a', 'z', 10))
                .setAddressInfoInputAddressAlias(RandomUtils.createString('a', 'z', 5))
                .setAddressInfoInputCity("Delaware");
    }

    public static CreateAnAccountPageState createRandomCreateAnAccountPageState() {
        return createRandomCreateAnAccountPageStateBuilder()
                .build();
    }
}
