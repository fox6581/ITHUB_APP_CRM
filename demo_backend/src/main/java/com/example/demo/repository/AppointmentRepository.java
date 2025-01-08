//package com.example.demo.repository;
//
//import com.example.demo.model.Appointment;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
//    // Найти записи по клиенту
//    List<Appointment> findByClientId(Long clientId);
//
//    // Найти записи по мастеру
//    List<Appointment> findByMasterId(Long masterId);
//
//    // Найти записи в указанном временном диапазоне
//    List<Appointment> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
//
//
//}
