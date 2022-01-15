package com.example.proiect.service;

import com.example.proiect.model.BankDetails;
import com.example.proiect.repository.BankDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class BankDetailsService {
    private final BankDetailsRepository bankDetailsRepository;

    public BankDetailsService(BankDetailsRepository bankDetailsRepository) {
        this.bankDetailsRepository = bankDetailsRepository;
    }

    public BankDetails saveBankDetails(BankDetails bankDetails) {
        return bankDetailsRepository.save(bankDetails);
    }

}
