package com.example.proiect.repository;

import com.example.proiect.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("select distinct c from Client c join Account a on c.clientId = a.client.clientId where a.accountCurrency = ?1")
    List<Client> findClientsByAccountCurrency(String currency);

    @Query("select c from Client c join Employee e on c.employee.employeeId = e.employeeId where e.employeeName = ?1")
    List<Client> findClientsByEmployeeName(String employeeName);
}
