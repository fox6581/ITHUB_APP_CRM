package com.example.demo.controller;

import com.example.demo.model.Qualification;
import com.example.demo.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    // Получение всех квалификаций
    @GetMapping
    public List<Qualification> getAllQualifications() {
        return qualificationService.getAllQualifications();
    }

    // Получение квалификации по ID
    @GetMapping("/{id}")
    public ResponseEntity<Qualification> getQualificationById(@PathVariable Long id) {
        Optional<Qualification> qualification = qualificationService.getQualificationById(id);
        return qualification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавление новой квалификации
    @PostMapping
    public ResponseEntity<Qualification> addQualification(@RequestBody Qualification qualification) {
        Qualification savedQualification = qualificationService.addQualification(qualification);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedQualification);
    }

    // Обновление существующей квалификации
    @PutMapping("/{id}")
    public ResponseEntity<Qualification> updateQualification(@PathVariable Long id, @RequestBody Qualification updatedQualification) {
        try {
            Qualification qualification = qualificationService.updateQualification(id, updatedQualification);
            return ResponseEntity.ok(qualification);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление квалификации
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        qualificationService.deleteQualification(id);
        return ResponseEntity.noContent().build();
    }
}
