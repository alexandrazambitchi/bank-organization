package com.example.proiect.controller;

import com.example.proiect.model.Account;
import com.example.proiect.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/new")
    public ResponseEntity<Account> saveAccount(@RequestBody Account account, @RequestParam int clientId) {
        return ResponseEntity.ok().body(accountService.saveAccount(account, clientId));
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> retrieveAccounts() {
        return ResponseEntity.ok().body(accountService.retrieveAccounts());
    }

    @GetMapping("/accountsInCurrency")
    public ResponseEntity<List<Account>> retrieveAccountsByCurrency(@RequestParam String currency){
        return ResponseEntity.ok().body(accountService.retrieveAccountsByCurrency(currency));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Boolean> deleteAccountById(@PathVariable int accountId){
        return ResponseEntity.ok().body(accountService.deleteAccountById(accountId));
    }

    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @RequestParam int clientId) {
        return ResponseEntity.ok().body(accountService.saveAccount(account, clientId));
    }

}
