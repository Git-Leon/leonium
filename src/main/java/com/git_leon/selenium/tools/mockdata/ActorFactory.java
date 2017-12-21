package com.git_leon.selenium.tools.mockdata;

/**
 * Created by leon on 8/17/17.
 */
public class ActorFactory {
    public static Actor createDefaultActor() {
        String firstName = ActorDataFactory.createUniqueName();
        String lastName = ActorDataFactory.createUniqueName();
        String email = ActorDataFactory.createEmailAddress(firstName, lastName);
        String addressLine1 = ActorDataFactory.createAddressLine();
        String addressLine2 = "";
        String city = "New Castle";
        String state = "DE";
        String zipcode = "19720";
        int socialSecurityNumber = ActorDataFactory.createSocialSecurityNumber();

        return new ActorBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setAddressLine1(addressLine1)
                .setAddressLine2(addressLine2)
                .setCity(city)
                .setState(state)
                .setZipcode(zipcode)
                .setSocialSecurityNumber(socialSecurityNumber)
                .build();
    }
}