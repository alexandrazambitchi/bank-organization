package com.example.proiect.model;

import javax.persistence.*;

@Entity
@Table(name = "bank_details")
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankDetailsId;
    private String address;
    private String city;

    public BankDetails() {
    }

    public BankDetails(String address, String city) {
        this.address = address;
        this.city = city;
    }

    public int getBankDetailsId() {
        return bankDetailsId;
    }

    public void setBankDetailsId(int bankDetailsId) {
        this.bankDetailsId = bankDetailsId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
