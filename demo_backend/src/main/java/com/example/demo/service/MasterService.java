package com.example.demo.service;

import com.example.demo.model.Master;
import com.example.demo.model.Qualification;
import com.example.demo.repository.MasterRepository;
import com.example.demo.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterService {

    private final MasterRepository masterRepository;
    private final QualificationRepository qualificationRepository;

    @Autowired
    public MasterService(MasterRepository masterRepository, QualificationRepository qualificationRepository) {
        this.masterRepository = masterRepository;
        this.qualificationRepository = qualificationRepository;
    }

    // Получение всех мастеров
    public List<Master> getAllMasters() {
        return masterRepository.findAll();
    }

    // Получение мастера по ID
    public Optional<Master> getMasterById(Long id) {
        return masterRepository.findById(id);
    }

    // Добавление нового мастера
    public Master addMaster(Master master) {
        return masterRepository.save(master);
    }

    // Обновление мастера
    public Master updateMaster(Long id, Master updatedMaster) {
        return masterRepository.findById(id)
                .map(existingMaster -> {
                    existingMaster.setName(updatedMaster.getName());
                    existingMaster.setPhoneNumber(updatedMaster.getPhoneNumber());

                    return masterRepository.save(existingMaster);
                })
                .orElseThrow(() -> new IllegalArgumentException("Master with ID " + id + " not found"));
    }

    // Удаление мастера
    public void deleteMaster(Long id) {
        masterRepository.deleteById(id);
    }

    // Добавление квалификации мастеру
    public Master addQualificationToMaster(Long masterId, Long qualificationId) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new IllegalArgumentException("Master with ID " + masterId + " not found"));
        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new IllegalArgumentException("Qualification with ID " + qualificationId + " not found"));

        return masterRepository.save(master);
    }

    // Удаление квалификации у мастера
    public Master removeQualificationFromMaster(Long masterId, Long qualificationId) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new IllegalArgumentException("Master with ID " + masterId + " not found"));
        Qualification qualification = qualificationRepository.findById(qualificationId)
                .orElseThrow(() -> new IllegalArgumentException("Qualification with ID " + qualificationId + " not found"));

        return masterRepository.save(master);
    }
}
