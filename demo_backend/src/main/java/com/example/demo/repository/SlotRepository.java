package com.example.demo.repository;

import com.example.demo.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findByWorkHours_WorkDate(LocalDate workDate);

    List<Slot> findByWorkHours_WorkDateAndMaster_Id(LocalDate workDate, Long masterId);
}
