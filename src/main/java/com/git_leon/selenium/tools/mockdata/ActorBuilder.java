package com.git_leon.selenium.tools.mockdata;

/**
 * Created by leon on 8/17/17.
 */
public class ActorBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipcode;
    private int socialSecurityNumber;

    public ActorBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ActorBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ActorBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ActorBuilder setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
        return this;
    }

    public ActorBuilder setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
        return this;
    }

    public ActorBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public ActorBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public ActorBuilder setZipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }

    public ActorBuilder setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
        return this;
    }

    public Actor build() {
        return new Actor(firstName, lastName, email, addressLine1, addressLine2, city, state, zipcode, socialSecurityNumber);
    }
}
