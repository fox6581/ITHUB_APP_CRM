//package com.example.demo.controller;
//
//import com.example.demo.model.Appointment;
//import com.example.demo.model.Clients;
//import com.example.demo.model.FreeSlot;
//import com.example.demo.repository.ClientsRepository;
//import com.example.demo.service.AppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/appointments")
//public class AppointmentController {
//
//    private final AppointmentService appointmentService;
//    private final ClientsRepository clientsRepository;
//
//    @Autowired
//    public AppointmentController(AppointmentService appointmentService, ClientsRepository clientsRepository) {
//        this.appointmentService = appointmentService;
//        this.clientsRepository = clientsRepository;
//    }
//
//    // Получить все записи
//    @GetMapping
//    public List<Appointment> getAllAppointments() {
//        return appointmentService.getAllAppointments();
//    }
//
//    // Получить запись по ID
//    @GetMapping("/{id}")
//    public Appointment getAppointmentById(@PathVariable Long id) {
//        return appointmentService.getAppointmentById(id).orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
//    }
//
//    // Создать новую запись
//    @PostMapping
//    public Appointment createAppointment(@RequestBody Appointment appointment) {
//        return appointmentService.addAppointment(appointment);
//    }
//
//    // Обновить запись
//    @PutMapping("/{id}")
//    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
//                                                         @RequestBody Appointment updatedAppointment,
//                                                         @RequestParam Long slotId) {
//        try {
//            // Передаем id, обновленную запись и slotId в сервис
//            Appointment updated = appointmentService.updateAppointment(id, updatedAppointment, slotId);
//            return ResponseEntity.ok(updated); // Возвращаем обновленную запись
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(null); // Если ошибка, возвращаем ошибку
//        }
//    }
//
//    // Удалить запись
//    @DeleteMapping("/{id}")
//    public void deleteAppointment(@PathVariable Long id) {
//        appointmentService.deleteAppointment(id);
//    }
//
//    // Получить доступные слоты для мастера в определённый промежуток времени
//    //   @GetMapping("/slots")
//    //public List<FreeSlot> getAvailableSlots(@RequestParam Long masterId, @RequestParam String start, @RequestParam String end) {
//    //   LocalDateTime startTime = LocalDateTime.parse(start);
//    //   LocalDateTime endTime = LocalDateTime.parse(end);
//    //   return appointmentService.getAvailableSlots(masterId, startTime, endTime);
//    //  }
//    @GetMapping("/slots")
//    public List<FreeSlot> getAvailableSlots(
//            @RequestParam Long masterId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime) {
//
//        return appointmentService.getAvailableSlots(masterId, startDate, endDate, startTime, endTime);
//    }
//
//    // Получение информации о слоте по ID
//    @GetMapping("/slots/{slotId}")
//    public ResponseEntity<FreeSlot> getSlotById(@PathVariable Long slotId) {
//        FreeSlot slot = appointmentService.getSlotById(slotId);
//        if (slot != null) {
//            return ResponseEntity.ok(slot); // Возвращаем статус 200 и данные слота
//        } else {
//            return ResponseEntity.notFound().build(); // Возвращаем 404, если слот не найден
//        }
//    }
//
//
//    @PostMapping("/bookSlot")
//    public ResponseEntity<?> bookSlot1(@RequestParam Long appointmentId,
//                                       @RequestParam Long slotId,
//                                       @RequestParam Long masterId,
//                                       @RequestParam Long qualificationServiceId,
//                                       @RequestParam Long masterQualificationServiceId) {
//        boolean isBooked = appointmentService.bookSlot1(appointmentId, slotId, masterId, qualificationServiceId, masterQualificationServiceId);
//
//        if (isBooked) {
//            return ResponseEntity.ok().body("Slot successfully booked.");
//        } else {
//            return ResponseEntity.status(400).body("Failed to book the slot.");
//        }
//    }
//
//
//
//
//    @PostMapping("/book/{slotId}/{masterId}/{qualificationId}/{serviceId}/{MasterQualificationServiceId}")
//    public ResponseEntity<String> bookSlot(
//            @PathVariable Long slotId,
//            @PathVariable Long masterId,
//            @PathVariable Long qualificationId,
//            @PathVariable Long serviceId,
//            //@PathVariable Long clientId,
//            @PathVariable Long MasterQualificationServiceId,
//            @RequestParam String name,
//            @RequestParam String phoneNumber,
//            @RequestParam String email
//            )
//    {
//
//        // Найти или создать клиента
//        Optional<Clients> existingClientOpt = clientsRepository.findByPhoneNumber(phoneNumber);
//        boolean success = appointmentService.bookSlot(slotId, masterId, qualificationId, serviceId, MasterQualificationServiceId, name, phoneNumber, email);
//
//        if (success) {
//            return ResponseEntity.ok("Slot booked successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot is already taken or invalid data provided");
//        }
//    }
//
//}
//
//    // Забронировать слот
////    @PostMapping("/{appointmentId}/book/{slotId}/{masterId}/{qualificationId}/{serviceId}")
////    public ResponseEntity<String> bookSlot(
////            @PathVariable Long appointmentId,
////            @PathVariable Long slotId,
////            @PathVariable Long masterId,
////            @PathVariable Long qualificationId,
////            @PathVariable Long serviceId) {
////
////        boolean success = appointmentService.bookSlot(appointmentId, slotId, masterId, qualificationId, serviceId);
////
////        if (success) {
////            return ResponseEntity.ok("Slot booked successfully");
////        } else {
////            return ResponseEntity.status(HttpStatus.CONFLICT).body("Slot is already taken or invalid data provided");
////        }
////    }
////
////}
//
//
