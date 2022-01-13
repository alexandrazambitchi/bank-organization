package com.example.proiect.repository;

import com.example.proiect.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.accountCurrency = ?1")
    List<Account> findAccountByCurrency(String currency);
}
