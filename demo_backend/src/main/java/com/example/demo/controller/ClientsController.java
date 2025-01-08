package com.example.demo.controller;

import com.example.demo.model.Clients;
import com.example.demo.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    // Получить всех клиентов
    @GetMapping
    public List<Clients> getAllClients() {
        return clientsService.getAllClients();
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClientById(@PathVariable Long id) {
        Optional<Clients> client = clientsService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить нового клиента
    @PostMapping
    public ResponseEntity<Clients> addClient(@RequestBody Clients client) {
        Clients createdClient = clientsService.addClient(client);
        return ResponseEntity.ok(createdClient);
    }


    // Обновить информацию о клиенте
    @PutMapping("/{id}")
    public ResponseEntity<Clients> updateClient(@PathVariable Long id, @RequestBody Clients clientDetails) {
        Clients updatedClient = clientsService.updateClient(id, clientDetails);
        return updatedClient != null ? ResponseEntity.ok(updatedClient) : ResponseEntity.notFound().build();
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        boolean isDeleted = clientsService.deleteClient(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
