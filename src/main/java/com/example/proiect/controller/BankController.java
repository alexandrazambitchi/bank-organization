package com.example.proiect.controller;

import com.example.proiect.model.*;
import com.example.proiect.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/new")
    public ResponseEntity<Bank> saveBank(@RequestBody Bank bank, @RequestParam int bankDetailsId) {
        return ResponseEntity.ok().body(bankService.saveBank(bank, bankDetailsId));
    }

    @GetMapping("/banks")
    public ResponseEntity<List<Bank>> retrieveBanks() {
        return ResponseEntity.ok().body(bankService.retrieveBanks());
    }


    @GetMapping("/banksInCity")
    public ResponseEntity<List<Bank>> retrieveBanksInCity(@RequestParam String city)
    {
        return ResponseEntity.ok().body(bankService.retrieveBanksByCity(city));
    }

    @PutMapping("/update")
    public ResponseEntity<Bank> updateBank(@RequestBody Bank bank, @RequestParam int bankDetailsId) {
        return ResponseEntity.ok().body(bankService.saveBank(bank, bankDetailsId));
    }

}
