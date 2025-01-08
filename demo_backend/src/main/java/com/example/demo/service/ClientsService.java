package com.example.demo.service;

import com.example.demo.model.Clients;
import com.example.demo.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsService {

    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    // Получить всех клиентов
    public List<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    // Получить клиента по ID
    public Optional<Clients> getClientById(Long id) {
        return clientsRepository.findById(id);
    }

    // Добавить нового клиента
    public Clients addClient(Clients client) {
        return clientsRepository.save(client);
    }

    // Обновить информацию о клиенте
    public Clients updateClient(Long id, Clients clientDetails) {
        Optional<Clients> clientOptional = clientsRepository.findById(id);
        if (clientOptional.isPresent()) {
            Clients existingClient = clientOptional.get();
            existingClient.setName(clientDetails.getName());
            existingClient.setPhoneNumber(clientDetails.getPhoneNumber());
            existingClient.setEmail(clientDetails.getEmail());
            return clientsRepository.save(existingClient);
        }
        return null;
    }

    // Удалить клиента
    public boolean deleteClient(Long id) {
        Optional<Clients> clientOptional = clientsRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientsRepository.delete(clientOptional.get());
            return true;
        }
        return false;
    }

    public Long findOrCreateClient(String name, String phoneNumber, String email) {
        // Ищем клиента сначала по номеру телефона
        Optional<Clients> clientOpt = clientsRepository.findByPhoneNumber(phoneNumber);

        if (clientOpt.isPresent()) {
            // Клиент найден, возвращаем его ID
            return clientOpt.get().getId();
        } else {
            // Клиент не найден, создаем нового
            Clients newClient = new Clients();
            newClient.setName(name);
            newClient.setPhoneNumber(phoneNumber);
            newClient.setEmail(email);
            Clients savedClient = clientsRepository.save(newClient);
            return savedClient.getId();
        }
    }
}
