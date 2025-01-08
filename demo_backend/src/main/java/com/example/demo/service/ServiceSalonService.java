package com.example.demo.service;

import com.example.demo.model.Service_salon;
import com.example.demo.repository.ServiceSalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceSalonService {

    private final ServiceSalonRepository serviceSalonRepository;

    @Autowired
    public ServiceSalonService(ServiceSalonRepository serviceSalonRepository) {
        this.serviceSalonRepository = serviceSalonRepository;
    }

    // Получить все услуги
    public List<Service_salon> getAllServices() {
        return serviceSalonRepository.findAll();
    }

    // Получить услугу по ID
    public Optional<Service_salon> getServiceById(Long id) {
        return serviceSalonRepository.findById(id);
    }

    // Добавить новую услугу
    public Service_salon addService(Service_salon service) {
        return serviceSalonRepository.save(service);
    }

    // Обновить услугу
    public Service_salon updateService(Long id, Service_salon updatedService) {
        return serviceSalonRepository.findById(id)
                .map(existingService -> {
                    existingService.setName(updatedService.getName());
                    existingService.setBasicPrice(updatedService.getBasicPrice());
                    existingService.setBasicTime(updatedService.getBasicTime());
                    return serviceSalonRepository.save(existingService);
                })
                .orElseThrow(() -> new IllegalArgumentException("Service with ID " + id + " not found"));
    }

    // Удалить услугу
    public void deleteService(Long id) {
        serviceSalonRepository.deleteById(id);
    }

    // Поиск услуг по названию
    public List<Service_salon> searchServicesByName(String name) {
        return serviceSalonRepository.findByNameContainingIgnoreCase(name);
    }
}
