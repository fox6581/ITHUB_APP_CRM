package com.example.demo.repository;

import com.example.demo.model.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Long> {
    // Дополнительный метод для поиска рабочих часов по дате
    List<WorkHours> findByWorkDate(LocalDate workDate);
}
