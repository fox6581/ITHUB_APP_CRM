package com.example.demo.controller;

import com.example.demo.model.Master;
import com.example.demo.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/masters")
public class MasterController {

    private final MasterService masterService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    // Получение всех мастеров
    @GetMapping
    public List<Master> getAllMasters() {
        return masterService.getAllMasters();
    }

    // Получение мастера по ID
    @GetMapping("/{id}")
    public ResponseEntity<Master> getMasterById(@PathVariable Long id) {
        Optional<Master> master = masterService.getMasterById(id);
        return master.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавление нового мастера
    @PostMapping
    public ResponseEntity<Master> addMaster(@RequestBody Master master) {
        Master savedMaster = masterService.addMaster(master);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMaster);
    }

    // Обновление мастера
    @PutMapping("/{id}")
    public ResponseEntity<Master> updateMaster(@PathVariable Long id, @RequestBody Master updatedMaster) {
        try {
            Master master = masterService.updateMaster(id, updatedMaster);
            return ResponseEntity.ok(master);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление мастера
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaster(@PathVariable Long id) {
        masterService.deleteMaster(id);
        return ResponseEntity.noContent().build();
    }

    // Добавление квалификации мастеру
    @PostMapping("/{masterId}/qualifications/{qualificationId}")
    public ResponseEntity<Master> addQualificationToMaster(@PathVariable Long masterId, @PathVariable Long qualificationId) {
        Master updatedMaster = masterService.addQualificationToMaster(masterId, qualificationId);
        return ResponseEntity.ok(updatedMaster);
    }

    // Удаление квалификации у мастера
    @DeleteMapping("/{masterId}/qualifications/{qualificationId}")
    public ResponseEntity<Master> removeQualificationFromMaster(@PathVariable Long masterId, @PathVariable Long qualificationId) {
        Master updatedMaster = masterService.removeQualificationFromMaster(masterId, qualificationId);
        return ResponseEntity.ok(updatedMaster);
    }
}
