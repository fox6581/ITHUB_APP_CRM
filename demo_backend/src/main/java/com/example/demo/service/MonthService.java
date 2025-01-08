package com.example.demo.service;

import com.example.demo.model.Month;
import com.example.demo.repository.MonthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonthService {

    @Autowired
    private MonthRepository monthRepository;

    // Метод для получения всех месяцев
    public List<Month> getAllMonths() {
        return monthRepository.findAll();
    }

    // Метод для получения месяца по ID
    public Optional<Month> getMonthById(Long id) {
        return monthRepository.findById(id);
    }



}
