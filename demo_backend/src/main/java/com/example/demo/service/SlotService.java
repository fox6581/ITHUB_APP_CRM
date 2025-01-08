package com.example.demo.service;

import com.example.demo.model.Slot;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SlotService {
    List<Slot> getSlotsByDate(LocalDate date);
    List<Slot> getSlotsByDateAndMaster(LocalDate date, Long masterId);
    boolean cancelBooking(Long slotId, Long clientId);
    // Проверка доступности слота
//    boolean isSlotAvailable(LocalDate date, Long masterId, LocalDate startTime, LocalDate endTime);
    // Измените параметр типа для времени с LocalDate на LocalTime
    boolean isSlotAvailable(LocalDate date, Long masterId, LocalTime startTime, LocalTime endTime);
    // Метод для бронирования слота
    boolean bookSlot(Long slotId, Long clientId, Long serviceId,  Double price);
}
