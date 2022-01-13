package com.example.proiect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;
    private String bankName;

    @OneToOne
    @JoinColumn(name = "bank_details_id")
    private BankDetails bankDetails;

    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Employee> employeeList = new ArrayList<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "bank_client", joinColumns = @JoinColumn(name = "bank_id"), inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clientList = new ArrayList<>();

    public Bank() {
    }

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
