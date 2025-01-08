package com.example.demo.repository;

import com.example.demo.model.MasterQualification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MasterQualificationRepository extends JpaRepository<MasterQualification, Long> {

    // Используем Optional в возвращаемом типе
    @EntityGraph(attributePaths = {"qualification", "master"})
    Optional<MasterQualification> findById(Long id);  // Возвращаем Optional
}
