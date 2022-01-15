package com.example.proiect.controller;

import com.example.proiect.model.Client;
import com.example.proiect.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/new")
    public ResponseEntity<Client> saveClient(@RequestBody Client client, @RequestParam int employeeId, @RequestParam int bankId) {
        return ResponseEntity.ok().body(clientService.saveClient(client, employeeId, bankId));
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> retrieveClients() {
        return ResponseEntity.ok().body(clientService.retrieveClients());
    }

    @GetMapping("/clientsByEmployee")
    public ResponseEntity<List<Client>> retrieveClientsByEmployeeName(@RequestParam String employeeName) {
        return ResponseEntity.ok().body(clientService.retrieveClientsByEmployeeName(employeeName));
    }

    @GetMapping("/clientsByAccountsInCurrency")
    public ResponseEntity<List<Client>> retrieveClientsByAccounts(@RequestParam String currency){
        return ResponseEntity.ok().body(clientService.retrieveClientsByAccounts(currency));
    }

    @DeleteMapping("/delete/{clientId}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable int clientId){
        return ResponseEntity.ok().body(clientService.deleteClientById(clientId));
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @RequestParam int employeeId, @RequestParam int bankId) {
        return ResponseEntity.ok().body(clientService.saveClient(client, employeeId, bankId));
    }
}
