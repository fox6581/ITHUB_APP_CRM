package com.example.demo.repository;

import com.example.demo.model.Service_salon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceSalonRepository extends JpaRepository<Service_salon, Long> {
    List<Service_salon> findByNameContainingIgnoreCase(String name);
    Optional<Service_salon> findById(Long id);
}
