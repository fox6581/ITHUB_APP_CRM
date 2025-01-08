package com.example.demo.repository;

import com.example.demo.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    // Дополнительные методы поиска можно добавить при необходимости
}
