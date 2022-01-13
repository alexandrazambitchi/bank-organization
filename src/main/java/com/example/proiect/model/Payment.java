package com.example.proiect.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private float paymentValue;
    private String paymentDetails;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;

    public Payment() {
    }

    public Payment(float paymentValue, String paymentDetails) {
        this.paymentValue = paymentValue;
        this.paymentDetails = paymentDetails;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public float getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(float paymentValue) {
        this.paymentValue = paymentValue;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
