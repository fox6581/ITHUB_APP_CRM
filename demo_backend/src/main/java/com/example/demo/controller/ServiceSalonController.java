package com.example.demo.controller;

import com.example.demo.model.Service_salon;
import com.example.demo.service.ServiceSalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/services")
public class ServiceSalonController {

    private final ServiceSalonService serviceSalonService;

    @Autowired
    public ServiceSalonController(ServiceSalonService serviceSalonService) {
        this.serviceSalonService = serviceSalonService;
    }

    // Получить все услуги
    @GetMapping
    public List<Service_salon> getAllServices() {
        return serviceSalonService.getAllServices();
    }

    // Получить услугу по ID
    @GetMapping("/{id}")
    public ResponseEntity<Service_salon> getServiceById(@PathVariable Long id) {
        Optional<Service_salon> service = serviceSalonService.getServiceById(id);
        return service.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Добавить новую услугу
    @PostMapping
    public ResponseEntity<Service_salon> addService(@RequestBody Service_salon service) {
        Service_salon savedService = serviceSalonService.addService(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedService);
    }

    // Обновить услугу
    @PutMapping("/{id}")
    public ResponseEntity<Service_salon> updateService(@PathVariable Long id, @RequestBody Service_salon updatedService) {
        try {
            Service_salon service = serviceSalonService.updateService(id, updatedService);
            return ResponseEntity.ok(service);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Удалить услугу
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceSalonService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    // Поиск услуг по названию
    @GetMapping("/search")
    public List<Service_salon> searchServices(@RequestParam String name) {
        return serviceSalonService.searchServicesByName(name);
    }
}
