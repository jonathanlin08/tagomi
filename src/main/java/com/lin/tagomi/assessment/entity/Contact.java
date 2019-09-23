package com.lin.tagomi.assessment.entity;

import org.springframework.util.NumberUtils;

public class Contact {

    private String firstName;
    private String lastName;
    private Long phone;
    private String address;

    public Contact(String fullName, String phoneString, String address) {
        String[] nameTokens = fullName.split(" ");
        if (nameTokens.length == 2) {
            this.firstName = nameTokens[0].trim();
            this.lastName = nameTokens[1].trim();
        } else {
            this.firstName = fullName.trim();
            this.lastName = "";
        }
        String phoneStringNumOnly = phoneString.replace("-","").trim();
        this.phone = NumberUtils.parseNumber(phoneStringNumOnly, Long.class);
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }
}
