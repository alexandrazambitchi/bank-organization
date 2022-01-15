package com.example.proiect.service;

import com.example.proiect.exception.ClientNotFoundException;
import com.example.proiect.model.Client;
import com.example.proiect.model.Payment;
import com.example.proiect.repository.ClientRepository;
import com.example.proiect.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;

    public PaymentService(PaymentRepository paymentRepository, ClientRepository clientRepository) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }


    public Payment savePayment(Payment payment, int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(ClientNotFoundException::new);

        payment.setClient(client);

        return paymentRepository.save(payment);
    }

    public List<Payment> retrievePayments(){
        return paymentRepository.findAll();
    }

    public boolean deletePaymentById(int paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found!!"));
        paymentRepository.deleteById(paymentId);
        return true;
    }
}
