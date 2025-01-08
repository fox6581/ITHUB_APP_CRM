package com.example.demo.service;

import com.example.demo.model.MasterQualification;
import com.example.demo.repository.MasterQualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterQualificationService {

    private final MasterQualificationRepository repository;

    @Autowired
    public MasterQualificationService(MasterQualificationRepository repository) {
        this.repository = repository;
    }

    // Получить все записи MasterQualification
    public List<MasterQualification> getAllMasterQualifications() {
        return repository.findAll();
    }

    // Получить запись по ID
    public Optional<MasterQualification> getMasterQualificationById(Long id) {
        return repository.findById(id);
    }

    // Добавить новую запись MasterQualification
    public MasterQualification addMasterQualification(MasterQualification masterQualification) {
        return repository.save(masterQualification);
    }

    // Удалить запись по ID
    public void deleteMasterQualification(Long id) {
        repository.deleteById(id);
    }
}
