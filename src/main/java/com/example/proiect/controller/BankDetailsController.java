package com.example.proiect.controller;

import com.example.proiect.model.BankDetails;
import com.example.proiect.service.BankDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankDetails")
public class BankDetailsController {
    private final BankDetailsService bankDetailsService;

    public BankDetailsController(BankDetailsService bankDetailsService) {
        this.bankDetailsService = bankDetailsService;
    }

    @PostMapping("/new")
    public ResponseEntity<BankDetails> saveBankDetails(@RequestBody BankDetails bankDetails) {
        return ResponseEntity.ok().body(bankDetailsService.saveBankDetails(bankDetails));
    }

    @PutMapping("/update")
    public ResponseEntity<BankDetails> updateBankDetails(@RequestBody BankDetails bankDetails) {
        return ResponseEntity.ok().body(bankDetailsService.saveBankDetails(bankDetails));
    }
}
