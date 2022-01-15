package com.example.proiect.service;

import com.example.proiect.model.*;
import com.example.proiect.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final BankDetailsRepository bankDetailsRepository;
    private final BankRepository bankRepository;

    public BankService(BankDetailsRepository bankDetailsRepository, BankRepository bankRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
        this.bankRepository = bankRepository;
    }

    public Bank saveBank(Bank bank, int bankDetailsId) {
        BankDetails bankDetails = bankDetailsRepository.findById(bankDetailsId).orElseThrow(() -> new RuntimeException("Id not valid!!"));

        bank.setBankDetails(bankDetails);
        return bankRepository.save(bank);
    }

    public List<Bank> retrieveBanks() {
        return bankRepository.findAll();
    }

    public List<Bank> retrieveBanksByCity(String city) {
        return bankRepository.findBanksByCity(city);
    }

}
