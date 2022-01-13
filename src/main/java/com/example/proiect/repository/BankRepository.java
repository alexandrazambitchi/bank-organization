package com.example.proiect.repository;

import com.example.proiect.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    @Query("select b from Bank b join BankDetails d on b.bankDetails.bankDetailsId = d.bankDetailsId where d.city = ?1")
    List<Bank> findBanksByCity(String city);
}
