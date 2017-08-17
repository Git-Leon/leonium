package com.zipcodewilmington.selenium.tools.mockdata;

/**
 * Created by leon on 8/17/17.
 */
public class Actor {
    private String firstName;
    private String lastName;
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipcode;
    private int socialSecurityNumber;

    public Actor(String firstName,
                 String lastName,
                 String email,
                 String addressLine1,
                 String addressLine2,
                 String city,
                 String state,
                 String zipcode,
                 int socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.socialSecurityNumber = socialSecurityNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
}
