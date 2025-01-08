package com.example.demo.controller;

import com.example.demo.model.Month;
import com.example.demo.service.MonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/months")
public class MonthController {

    @Autowired
    private MonthService monthService;

    // Получение всех месяцев
    @GetMapping
    public List<Month> getAllMonths() {
        return monthService.getAllMonths();
    }

    // Получение месяца по ID
    @GetMapping("/{id}")
    public ResponseEntity<Month> getMonthById(@PathVariable("id") Long id) {
        Optional<Month> month = monthService.getMonthById(id);
        return month.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
