package com.example.demo.service;

import com.example.demo.model.Master;
import com.example.demo.model.MasterQualification;
import com.example.demo.model.Service_salon;
import com.example.demo.model.MasterQualificationService;
import com.example.demo.repository.MasterQualificationServiceRepository;
import com.example.demo.repository.MasterQualificationRepository;
import com.example.demo.repository.ServiceSalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterQualificationServiceService {

    private final MasterQualificationServiceRepository repository;
    private final MasterQualificationRepository masterQualificationRepository;
    private final ServiceSalonRepository serviceSalonRepository;

    @Autowired
    public MasterQualificationServiceService(MasterQualificationServiceRepository repository,
                                             MasterQualificationRepository masterQualificationRepository,
                                             ServiceSalonRepository serviceSalonRepository) {
        this.repository = repository;
        this.masterQualificationRepository = masterQualificationRepository;
        this.serviceSalonRepository = serviceSalonRepository;
    }

//    public List<Master> getMastersByServiceId(Long serviceId) {
//        return repository.findMastersByServiceId(serviceId);
//    }

    // Получение MasterQualificationService по ID
    public Optional<MasterQualificationService> getMasterQualificationServiceById(Long id) {
        return repository.findById(id);
    }

    // Получение MasterQualification по ID
    public MasterQualification getMasterQualificationById(Long id) {
        return masterQualificationRepository.findById(id).orElse(null);
    }

    // Получение Service_salon по ID
    public Service_salon getServiceById(Long id) {
        return serviceSalonRepository.findById(id).orElse(null);
    }

    // В MasterQualificationServiceService.java
    public Iterable<MasterQualificationService> getAllMasterQualificationServices() {
        return repository.findAll(); // Используется репозиторий для получения всех записей
    }

    // Создание или обновление MasterQualificationService
    public MasterQualificationService saveMasterQualificationService(MasterQualificationService service) {
        service.calculatePriceAndTime(); // Вычисление цены и времени
        return repository.save(service);
    }

    // Удаление MasterQualificationService
    public void deleteMasterQualificationService(Long id) {
        repository.deleteById(id);
    }
}
