package com.example.demo.service;

import com.example.demo.model.Qualification;
import com.example.demo.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationService {

    private final QualificationRepository qualificationRepository;

    @Autowired
    public QualificationService(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    // Получение всех квалификаций
    public List<Qualification> getAllQualifications() {
        return qualificationRepository.findAll();
    }

    // Получение квалификации по ID
    public Optional<Qualification> getQualificationById(Long id) {
        return qualificationRepository.findById(id);
    }

    // Добавление новой квалификации
    public Qualification addQualification(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    // Обновление существующей квалификации
    public Qualification updateQualification(Long id, Qualification updatedQualification) {
        return qualificationRepository.findById(id)
                .map(existingQualification -> {
                    existingQualification.setName(updatedQualification.getName());
                    existingQualification.setPrice(updatedQualification.getPrice());
                    existingQualification.setTime(updatedQualification.getTime());
                    return qualificationRepository.save(existingQualification);
                })
                .orElseThrow(() -> new IllegalArgumentException("Qualification with ID " + id + " not found"));
    }

    // Удаление квалификации по ID
    public void deleteQualification(Long id) {
        qualificationRepository.deleteById(id);
    }
}
