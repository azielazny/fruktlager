package com.fruktlager.model;

public class Address {
    private String firstName, lastName, line1, line2, country, city, postalCode, phone;

    public Address(String firstName, String lastName, String line1, String line2, String country, String city, String postalCode, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.line1 = line1;
        this.line2 = line2;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    public Address(String line1, String line2, String country, String city, String postalCode, String phone) {
        this.line1 = line1;
        this.line2 = line2;
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return firstName + ";" + lastName + ";" + line1 + ";" + line2 + ";" + country + ";" + city + ";" + postalCode + ";" + phone;
    }
}
