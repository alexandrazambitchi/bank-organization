package com.example.proiect.controller;

import com.example.proiect.model.Payment;
import com.example.proiect.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/new")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment, @RequestParam int clientId){
        return ResponseEntity.ok().body(paymentService.savePayment(payment, clientId));
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> retrievePayments() {
        return ResponseEntity.ok().body(paymentService.retrievePayments());
    }

    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<Boolean> deletePaymentById(@PathVariable int paymentId){
        return ResponseEntity.ok().body(paymentService.deletePaymentById(paymentId));
    }

    @PutMapping("/update")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment, @RequestParam int clientId){
        return ResponseEntity.ok().body(paymentService.savePayment(payment, clientId));
    }
}
