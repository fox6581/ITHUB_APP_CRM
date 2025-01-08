package com.example.demo.controller;

import com.example.demo.model.Master;
import com.example.demo.model.MasterQualificationService;
import com.example.demo.repository.MasterQualificationServiceRepository;
import com.example.demo.service.MasterQualificationServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/master-qualification-services")
public class MasterQualificationServiceController {

    private final MasterQualificationServiceService service;
    private final MasterQualificationServiceRepository masterQualificationServiceRepository;

    @Autowired
    public MasterQualificationServiceController(MasterQualificationServiceService service,
                                                MasterQualificationServiceRepository masterQualificationServiceRepository) {
        this.service = service;
        this.masterQualificationServiceRepository = masterQualificationServiceRepository;
    }


    // Получение MasterQualificationService по ID
    @GetMapping("/{id}")
    public ResponseEntity<MasterQualificationService> getMasterQualificationService(@PathVariable Long id) {
        Optional<MasterQualificationService> masterQualificationService = service.getMasterQualificationServiceById(id);
        return masterQualificationService
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<MasterQualificationService>> getAllMasterQualificationServices() {
        Iterable<MasterQualificationService> services = service.getAllMasterQualificationServices();
        return ResponseEntity.ok(services);
    }

    // Получение всех мастеров по ID услуги
    @GetMapping("/{serviceId}/masters")
    public ResponseEntity<List<Map<String, Object>>> getMastersForService(@PathVariable Long serviceId) {
        List<Object[]> results = masterQualificationServiceRepository.findMastersWithServiceDetailsByServiceId(serviceId);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<Map<String, Object>> mastersDetails = results.stream()
                .map(result -> {
                    Map<String, Object> masterDetail = new HashMap<>();
                    Master master = (Master) result[0];  // Мастер
                    masterDetail.put("master", master); // Присваиваем мастера
                    masterDetail.put("qualification", result[1]);
                    masterDetail.put("price", result[2]);  // Цена из MasterQualificationService
                    masterDetail.put("estimatedTime", result[3]); // Время из MasterQualificationService
                    return masterDetail;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(mastersDetails);
    }

//    @GetMapping("/{serviceId}/masters")
//    public ResponseEntity<List<Map<String, Object>>> getMastersForService(@PathVariable Long serviceId) {
//        List<Object[]> results = masterQualificationServiceRepository.findMastersWithServiceDetailsByServiceId(serviceId);
//
//        if (results.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//
//        List<Map<String, Object>> mastersDetails = results.stream()
//                .map(result -> {
//                    Map<String, Object> masterDetail = new HashMap<>();
//                    Master master = (Master) result[0];  // Мастер
//                    masterDetail.put("master", master); // Присваиваем мастера
//
//                    masterDetail.put("price", result[1]);  // Цена из MasterQualificationService
//                    masterDetail.put("estimatedTime", result[2]); // Время из MasterQualificationService
//
//                    // Проверяем квалификацию
//                    if (result[3] != null) {
//                        Map<String, Object> qualification = new HashMap<>();
//                        qualification.put("name", result[3]); // Если квалификация есть, создаем объект с полем name
//                        masterDetail.put("qualification", qualification);
//                    } else {
//                        masterDetail.put("qualification", new HashMap<>()); // Если квалификация нет, возвращаем пустой объект
//                    }
//
//                    return masterDetail;
//                })
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(mastersDetails);
//    }

    // Создание или обновление MasterQualificationService
    @PostMapping
    public ResponseEntity<MasterQualificationService> createOrUpdateMasterQualificationService(@RequestBody MasterQualificationService masterQualificationService) {
        MasterQualificationService savedService = service.saveMasterQualificationService(masterQualificationService);
        return ResponseEntity.ok(savedService);
    }

    // Удаление MasterQualificationService
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMasterQualificationService(@PathVariable Long id) {
        service.deleteMasterQualificationService(id);
        return ResponseEntity.noContent().build();
    }
}
