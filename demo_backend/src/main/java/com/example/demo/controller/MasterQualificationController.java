package com.example.demo.controller;

import com.example.demo.model.MasterQualification;
import com.example.demo.service.MasterQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/master-qualifications")
public class MasterQualificationController {

    private final MasterQualificationService masterQualificationService;

    @Autowired
    public MasterQualificationController(MasterQualificationService masterQualificationService) {
        this.masterQualificationService = masterQualificationService;
    }

    // Получить все записи MasterQualification
    @GetMapping
    public List<MasterQualification> getAllMasterQualifications() {
        return masterQualificationService.getAllMasterQualifications();
    }

    // Получить запись по ID
    @GetMapping("/{id}")
    public ResponseEntity<MasterQualification> getMasterQualificationById(@PathVariable Long id) {
        Optional<MasterQualification> masterQualification = masterQualificationService.getMasterQualificationById(id);
        return masterQualification.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новую запись MasterQualification
    @PostMapping
    public ResponseEntity<MasterQualification> addMasterQualification(@RequestBody MasterQualification masterQualification) {
        MasterQualification savedMasterQualification = masterQualificationService.addMasterQualification(masterQualification);
        return new ResponseEntity<>(savedMasterQualification, HttpStatus.CREATED);
    }

    // Удалить запись MasterQualification по ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterQualification(@PathVariable Long id) {
        masterQualificationService.deleteMasterQualification(id);
        return ResponseEntity.noContent().build();
    }
}
