package com.example.proiect.repository;

import com.example.proiect.model.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Integer> {
}
