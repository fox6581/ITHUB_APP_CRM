package com.example.demo.repository;

import com.example.demo.model.Clients;
import com.example.demo.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientsRepository extends JpaRepository<Clients, Long> {
//    Optional<Clients> findByEmail(String email); // Ищет клиента по email, возвращает Optional
    Optional<Clients> findByPhoneNumber(String phoneNumber); // Ищет клиента по номеру телефона, возвращает Optional
}
