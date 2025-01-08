package com.example.demo.repository;

import com.example.demo.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterRepository extends JpaRepository<Master, Long> {
    // Дополнительные запросы при необходимости
}