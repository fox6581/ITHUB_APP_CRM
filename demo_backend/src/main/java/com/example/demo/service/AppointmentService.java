//package com.example.demo.service;
//
//import com.example.demo.model.*;
//import com.example.demo.repository.*;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//public class AppointmentService {
//    private static final Logger logger = LoggerFactory.getLogger(AppointmentService.class);
//
//    private final AppointmentRepository appointmentRepository;
//    private final FreeSlotRepository freeSlotRepository;
//    @Autowired
//    private ClientsService clientsService;
//
//    @Autowired
//    public AppointmentService(AppointmentRepository appointmentRepository, FreeSlotRepository freeSlotRepository) {
//        this.appointmentRepository = appointmentRepository;
//        this.freeSlotRepository = freeSlotRepository;
//    }
//
//    @Autowired
//    private ClientsRepository clientsRepository;
//
//    @Autowired
//    private MasterRepository masterRepository;
//
//    @Autowired
//    private SlotRepository slotRepository; // Добавляем SlotRepository
//
//    // Добавьте репозиторий для MasterQualificationService
//    @Autowired
//    private MasterQualificationServiceRepository masterQualificationServiceRepository;
//    // Получить все записи
//
//
//    @Autowired
//    private QualificationRepository qualificationRepository;
//
//
//    @Autowired
//    private ClientsRepository clientRepository;
//
//    @Autowired
//    private ServiceSalonRepository serviceSalonRepository;
//
//
//    public List<Appointment> getAllAppointments() {
//        return appointmentRepository.findAll();
//    }
//
//    // Получить запись по ID
//    public Optional<Appointment> getAppointmentById(Long id) {
//        return appointmentRepository.findById(id);
//    }
//
//    // Добавить новую запись
//    public Appointment addAppointment(Appointment appointment) {
//        return appointmentRepository.save(appointment);
//    }
//
//
//    // Обновить запись
//    public Appointment updateAppointment(Long id, Appointment updatedAppointment, Long slotId) {
//        // Ищем запись в базе данных по ID
//        Appointment updatedAppointmentResult = appointmentRepository.findById(id)
//                .map(existingAppointment -> {
//                    // Обновляем поля существующей записи на основе переданных данных
//                    Clients client = clientRepository.findById(updatedAppointment.getClient().getId())
//                            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
//                    Master master = masterRepository.findById(updatedAppointment.getMaster().getId())
//                            .orElseThrow(() -> new IllegalArgumentException("Master not found"));
//                    Qualification qualification = qualificationRepository.findById(updatedAppointment.getQualification().getId())
//                            .orElseThrow(() -> new IllegalArgumentException("Qualification not found"));
//                    Service_salon serviceSalon = serviceSalonRepository.findById(updatedAppointment.getServiceSalon().getId())
//                            .orElseThrow(() -> new IllegalArgumentException("ServiceSalon not found"));
////                    MasterQualificationService masterQualificationService = masterQualificationServiceRepository
////                            .findById(updatedAppointment.getMasterQualificationService().getId())
////                            .orElseThrow(() -> new IllegalArgumentException("MasterQualificationService not found"));
//
//                    // Получаем старый слот, если он существует
//                    FreeSlot oldSlot = existingAppointment.getFreeSlot();
//                    if (oldSlot != null) {
//                        // Освобождаем старый слот
//                        oldSlot.setStatus("Свободно");
//                        freeSlotRepository.save(oldSlot);
//                    }
//
//                    // Проверяем наличие свободного слота по ID
//                    FreeSlot freeSlot = freeSlotRepository.findById(slotId)
//                            .orElseThrow(() -> new IllegalArgumentException("Slot with ID " + slotId + " not found"));
//
//                    // Проверяем, доступен ли слот для бронирования
//                    if (!"Свободно".equals(freeSlot.getStatus())) {
//                        logger.warn("Slot with ID {} is already booked. Current status: {}", slotId, freeSlot.getStatus());
//                        throw new IllegalStateException("The slot with ID " + slotId + " is already booked. Please select another slot.");
//                    }
//
//                    // Устанавливаем новый статус слота на "Занято"
//                    freeSlot.setStatus("Занято");
//                    freeSlotRepository.save(freeSlot);
//
//                    // Обновляем существующую запись с новыми значениями
//                    existingAppointment.setClient(client);
//                    existingAppointment.setMaster(master);
//                    existingAppointment.setQualification(qualification);
//                    existingAppointment.setServiceSalon(serviceSalon);
////                    existingAppointment.setMasterQualificationService(masterQualificationService);
//                    existingAppointment.setFreeSlot(freeSlot);
//                    existingAppointment.setDateTime(updatedAppointment.getDateTime());
//
//                    // Сохраняем обновленную запись в базе данных
//                    return appointmentRepository.save(existingAppointment);
//                })
//                .orElseThrow(() -> new IllegalArgumentException("Appointment with ID " + id + " not found"));
//
//        // Если запись успешно обновлена, возвращаем ее
//        return updatedAppointmentResult;
//    }
//
//
//
//    // Удалить запись
//    public void deleteAppointment(Long id) {
//        appointmentRepository.deleteById(id);
//    }
//
//    // Найти записи по клиенту
//    public List<Appointment> getAppointmentsByClientId(Long clientId) {
//        return appointmentRepository.findByClientId(clientId);
//    }
//
//    // Найти записи по мастеру
//    public List<Appointment> getAppointmentsByMasterId(Long masterId) {
//        return appointmentRepository.findByMasterId(masterId);
//    }
//
//    // Найти записи в указанном временном диапазоне
//    public List<Appointment> getAppointmentsByDateRange(LocalDateTime start, LocalDateTime end) {
//        return appointmentRepository.findByDateTimeBetween(start, end);
//    }
//
//    // Получить слот по ID
//    public FreeSlot getSlotById(Long slotId) {
//        Optional<FreeSlot> slot = freeSlotRepository.findById(slotId);
//        return slot.orElse(null); // Если слот найден, возвращаем, если нет - null
//    }
//
//    // Получить доступные слоты для мастера в определённый промежуток времени
//    public List<FreeSlot> getAvailableSlots(Long masterId, LocalDate startDate, LocalDate endDate,
//                                            LocalTime startTime, LocalTime endTime) {
//        // Преобразуем LocalDate и LocalTime в LocalDateTime для точной фильтрации.
//        LocalDateTime startDateTime = startDate.atTime(startTime);
//        LocalDateTime endDateTime = endDate.atTime(endTime);
//
//        return freeSlotRepository.findByMasterIdAndDateBetweenAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(
//                masterId, startDate, endDate, startTime, endTime);
//    }
//
//
//    public boolean bookSlot1(Long appointmentId, Long slotId, Long masterId, Long qualificationServiceId, long masterQualificationServiceId) {
//        logger.info("Attempting to book slot with ID {} for appointment ID {}", slotId, appointmentId);
//
//        Optional<FreeSlot> freeSlotOpt = freeSlotRepository.findById(slotId);
//        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
//        Optional<Master> masterOpt = masterRepository.findById(masterId);
//
////        Optional<MasterQualificationService> qualificationServiceOpt = masterQualificationServiceRepository.findById(masterQualificationServiceId);
//
//        if (freeSlotOpt.isPresent() && appointmentOpt.isPresent() && masterOpt.isPresent() ) {
//            FreeSlot freeSlot = freeSlotOpt.get();
//            Appointment appointment = appointmentOpt.get();
//            Master master = masterOpt.get();
////            MasterQualificationService qualificationService = qualificationServiceOpt.get(); // Получаем квалификацию мастера
//
//            logger.debug("Found free slot: {} and appointment: {} and master: {} with qualification: {}", freeSlot, appointment, master, qualificationService);
//
//            if (appointment.getDateTime() == null) {
//                logger.info("Appointment with ID {} has no dateTime set. Attempting to assign slot.", appointmentId);
//
//                LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), freeSlot.getStartTime());
//                appointment.setDateTime(dateTime);
//
//                if ("Свободно".equals(freeSlot.getStatus())) {
//                    logger.info("Slot with ID {} is free. Updating status to 'Занято'.", slotId);
//                    freeSlot.setStatus("Занято");
//                    appointment.setFreeSlot(freeSlot);  // Привязываем слот к записи
//                    appointment.setMaster(master);  // Привязываем мастера к записи
//                 //   appointment.setQualification(qualificationService);
//                    appointment.setMasterQualificationService(qualificationService);  // Привязываем квалификацию мастера
//                    freeSlotRepository.save(freeSlot);
//
//                    appointmentRepository.save(appointment);
//                    logger.info("Successfully booked slot with ID {} for appointment ID {}", slotId, appointmentId);
//                    return true;
//                } else {
//                    logger.warn("Slot with ID {} is not free. Current status: {}", slotId, freeSlot.getStatus());
//                    return false;
//                }
//            } else {
//                logger.warn("Appointment with ID {} already has a dateTime set. Slot cannot be booked.", appointmentId);
//                return false;
//            }
//        }
//
//        if (freeSlotOpt.isEmpty()) {
//            logger.error("Slot with ID {} not found.", slotId);
//        }
//        if (appointmentOpt.isEmpty()) {
//            logger.error("Appointment with ID {} not found.", appointmentId);
//        }
//        if (masterOpt.isEmpty()) {
//            logger.error("Master with ID {} not found.", masterId);
//        }
//        if (qualificationServiceOpt.isEmpty()) {
//            logger.error("Qualification service with ID {} not found.", qualificationServiceId);
//        }
//
//        return false;
//    }
//
//
//
//    //запись на услугу время слоты, мастер и тд и тп
//    public boolean bookSlot(Long slotId, Long masterId, Long qualificationId, Long serviceId, Long MasterQualificationServiceId, String name, String phoneNumber, String email) {
//        logger.info("Attempting to book slot with ID {}", slotId);
//
//        // Проверяем наличие слота
//        Optional<FreeSlot> freeSlotOpt = freeSlotRepository.findById(slotId);
//        if (freeSlotOpt.isEmpty()) {
//            logger.error("Slot with ID {} not found.", slotId);
//            return false;
//        }
//
//        FreeSlot freeSlot = freeSlotOpt.get();
//
//        // Проверяем статус слота
//        if (!"Свободно".equals(freeSlot.getStatus())) {
//            logger.warn("Slot with ID {} is not free. Current status: {}", slotId, freeSlot.getStatus());
//            return false;
//        }
//
//        // Проверяем мастера, квалификацию, услугу и клиента
//        Optional<Master> masterOpt = masterRepository.findById(masterId);
//        Optional<Qualification> qualificationOpt = qualificationRepository.findById(qualificationId);
//        Optional<Service_salon> serviceOpt = serviceSalonRepository.findById(serviceId);
//        Optional<MasterQualificationService> msqOpt = masterQualificationServiceRepository.findById(MasterQualificationServiceId);
//        if (masterOpt.isEmpty() || qualificationOpt.isEmpty() || serviceOpt.isEmpty() || msqOpt.isEmpty()) {
//            logger.error("Invalid master, qualification, or service ID provided. Master ID: {}, Qualification ID: {}, Service ID: {}, Client ID: {}",
//                    masterId, qualificationId, serviceId);
//            return false;
//        }
//
//        Master master = masterOpt.get();
//        MasterQualificationService masterQualificationService = msqOpt.get();
//        Qualification qualification = qualificationOpt.get();
//        Service_salon service = serviceOpt.get();
//
//        // Найдем или создадим клиента
//        Long clientId = clientsService.findOrCreateClient(name, phoneNumber, email);
//
//        // Получаем объект Clients из репозитория по ID клиента
//        Optional<Clients> clientOpt = clientsRepository.findById(clientId);
//        if (clientOpt.isEmpty()) {
//            logger.error("Client with ID {} not found", clientId);
//            return false; // Если клиента не нашли, возвращаем false или обрабатываем ошибку
//        }
//
//        Clients client = clientOpt.get();
//
//        // Создаем новую запись Appointment
//        Appointment appointment = new Appointment();
//        appointment.setMaster(master);
//        appointment.setQualification(qualification);
//        appointment.setServiceSalon(service);
//        appointment.setMasterQualificationService(masterQualificationService);
//        appointment.setClient(client);  // Устанавливаем клиента
//
//        // Привязываем слот к записи
//        appointment.setFreeSlot(freeSlot);
//        appointment.setDateTime(LocalDateTime.of(LocalDate.now(), freeSlot.getStartTime()));
//
//        // Обновляем статус слота
//        freeSlot.setStatus("Занято");
//
//        try {
//            // Сохраняем appointment в базе данных
//            appointmentRepository.save(appointment);
//
//            // Обновляем статус слота
//            freeSlotRepository.save(freeSlot);
//
//            logger.info("Successfully booked slot with ID {}", slotId);
//            return true;
//        } catch (Exception e) {
//            logger.error("Error occurred while booking slot with ID {}: {}", slotId, e.getMessage());
//            return false;
//        }
//    }
//
//
//
////Блокировка времени по записи относительно мастерсервисквалификэйшн
//
////    // Метод для бронирования слота с учетом продолжительности услуги
////    public boolean bookSlotWithDuration(Long slotId, Long masterId, Long clientId, Long serviceId, int estimatedTime) {
////        // Получаем начальный слот
////        Optional<FreeSlot> freeSlotOptional = freeSlotRepository.findById(slotId);
////        if (freeSlotOptional.isEmpty()) {
////            throw new NoSuchElementException("Slot not found");
////        }
////        FreeSlot freeSlot = freeSlotOptional.get();
////
////        // Проверяем, свободен ли слот
////        if (!"Свободно".equals(freeSlot.getStatus())) {
////            throw new IllegalStateException("Slot is not available");
////        }
////
////        // Получаем информацию об услуге
////        Optional<Service_salon> serviceOptional = serviceSalonRepository.findById(serviceId);
////        if (serviceOptional.isEmpty()) {
////            throw new NoSuchElementException("Service not found");
////        }
////        Service_salon serviceSalon = serviceOptional.get();
////
////        // Получаем информацию о клиенте
////        Optional<Clients> clientOptional = clientsRepository.findById(clientId);
////        if (clientOptional.isEmpty()) {
////            throw new NoSuchElementException("Client not found");
////        }
////        Clients client = clientOptional.get();
////
////        // Получаем информацию о мастере
////        Optional<Master> masterOptional = masterRepository.findById(masterId);
////        if (masterOptional.isEmpty()) {
////            throw new NoSuchElementException("Master not found");
////        }
////        Master master = masterOptional.get();
////
////        // Получаем квалификацию мастера через MasterQualificationService
////        Optional<MasterQualificationService> qualificationServiceOptional = masterQualificationServiceRepository.findByServiceIdAndMasterId(serviceId, masterId);
////        if (qualificationServiceOptional.isEmpty()) {
////            throw new NoSuchElementException("Qualification service not found");
////        }
////        MasterQualificationService masterQualificationService = qualificationServiceOptional.get();
////
////        // Получаем список всех слотов мастера на текущий день, отсортированных по времени
////        List<FreeSlot> slots = freeSlotRepository.findByMasterIdAndDateBetweenAndStartTimeGreaterThanEqualAndEndTimeLessThanEqual(masterId, freeSlot.getDate());
////        if (slots.isEmpty()) {
////            throw new NoSuchElementException("No slots available for the master on the selected day");
////        }
////
////        // Находим начальный индекс выбранного слота
////        int startIndex = slots.indexOf(freeSlot);
////        if (startIndex == -1) {
////            throw new IllegalStateException("Slot not found in the master's schedule");
////        }
////
////        // Проверяем последовательность слотов для обеспечения длительности услуги
////        int accumulatedTime = 0;
////        int endIndex = startIndex;
////        for (int i = startIndex; i < slots.size(); i++) {
////            FreeSlot currentSlot = slots.get(i);
////            if (!"Свободно".equals(currentSlot.getStatus())) {
////                throw new IllegalStateException("Not enough consecutive free slots available");
////            }
////
////            accumulatedTime += currentSlot.getEndTime(); // Предполагается, что слот имеет поле duration
////            if (accumulatedTime >= estimatedTime) {
////                endIndex = i;
////                break;
////            }
////        }
////
////        // Если недостаточно времени
////        if (accumulatedTime < estimatedTime) {
////            throw new IllegalStateException("Not enough time available to book the service");
////        }
////
////        // Помечаем слоты как занятые
////        List<FreeSlot> bookedSlots = slots.subList(startIndex, endIndex + 1);
////        for (FreeSlot slot : bookedSlots) {
////            slot.setStatus("Занято");
////        }
////        freeSlotRepository.saveAll(bookedSlots);
////
////        // Создаем Appointment
////        Appointment appointment = new Appointment();
////        appointment.setClient(client);
////        appointment.setMaster(master);
////        appointment.setServiceSalon(serviceSalon);
////        appointment.setMasterQualificationService(masterQualificationService);
////        appointment.setFreeSlot(freeSlot);
////        appointment.setDateTime(LocalDateTime.of(freeSlot.getDate(), freeSlot.getStartTime()));
////        appointment.setDuration(estimatedTime);
////
////        // Сохраняем запись
////        appointmentRepository.save(appointment);
////
////        return true;
////    }
//}
//
//
//
////    public boolean bookSlot(Long appointmentId, Long slotId, Long masterId, Long qualificationId, Long serviceId) {
////        logger.info("Attempting to book slot with ID {} for appointment ID {}", slotId, appointmentId);
////
////        // Получение записи, слота, мастера, квалификации и услуги
////        Optional<FreeSlot> freeSlotOpt = freeSlotRepository.findById(slotId);
////        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
////        Optional<Master> masterOpt = masterRepository.findById(masterId);
////        Optional<Qualification> qualificationOpt = qualificationRepository.findById(qualificationId);
////        Optional<Service_salon> serviceOpt = serviceSalonRepository.findById(serviceId);
////
////        // Проверка наличия всех необходимых сущностей
////        if (freeSlotOpt.isPresent() && appointmentOpt.isPresent() && masterOpt.isPresent() &&
////                qualificationOpt.isPresent() && serviceOpt.isPresent()) {
////
////            FreeSlot freeSlot = freeSlotOpt.get();
////            Appointment appointment = appointmentOpt.get();
////            Master master = masterOpt.get();
////            Qualification qualification = qualificationOpt.get();
////            Service_salon serviceSalon = serviceOpt.get();
////
////            logger.debug("Found entities: FreeSlot={}, Appointment={}, Master={}, Qualification={}, Service={}",
////                    freeSlot, appointment, master, qualification, serviceSalon);
////
////            // Проверка, есть ли уже установленное время у записи
////            if (appointment.getDateTime() == null) {
////                logger.info("Appointment with ID {} has no dateTime set. Attempting to assign slot.", appointmentId);
////
////                // Установка времени на основе слота
////                LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), freeSlot.getStartTime());
////                appointment.setDateTime(dateTime);
////
////                // Проверка статуса слота
////                if ("Свободно".equals(freeSlot.getStatus())) {
////                    logger.info("Slot with ID {} is free. Updating status to 'Занято'.", slotId);
////
////                    // Установка мастера, квалификации, услуги и слота для записи
////                    appointment.setMaster(master);
////                    appointment.setQualification(qualification);
////                    appointment.setServiceSalon(serviceSalon);
////                    appointment.setFreeSlot(freeSlot);
////
////                    // Обновление статуса слота
////                    freeSlot.setStatus("Занято");
////                    freeSlotRepository.save(freeSlot);
////
////                    // Сохранение изменений в записи
////                    appointmentRepository.save(appointment);
////
////                    logger.info("Successfully booked slot with ID {} for appointment ID {}", slotId, appointmentId);
////                    return true;
////                } else {
////                    logger.warn("Slot with ID {} is not free. Current status: {}", slotId, freeSlot.getStatus());
////                    return false;
////                }
////            } else {
////                logger.warn("Appointment with ID {} already has a dateTime set. Slot cannot be booked.", appointmentId);
////                return false;
////            }
////        }
////
////        // Логирование ошибок, если какая-либо из сущностей не найдена
////        if (freeSlotOpt.isEmpty()) {
////            logger.error("Slot with ID {} not found.", slotId);
////        }
////        if (appointmentOpt.isEmpty()) {
////            logger.error("Appointment with ID {} not found.", appointmentId);
////        }
////        if (masterOpt.isEmpty()) {
////            logger.error("Master with ID {} not found.", masterId);
////        }
////        if (qualificationOpt.isEmpty()) {
////            logger.error("Qualification with ID {} not found.", qualificationId);
////        }
////        if (serviceOpt.isEmpty()) {
////            logger.error("Service with ID {} not found.", serviceId);
////        }
////
////        return false;
////    }
//
//
//
//
////    запись на услугу время слоты, мастер и тд и тп
////    public boolean bookSlot(Long slotId, Long masterId, Long qualificationId, Long serviceId, Long clientId, Long MasterQualificationServiceId) {
////        logger.info("Attempting to book slot with ID {}", slotId);
////
////        // Проверяем наличие слота
////        Optional<FreeSlot> freeSlotOpt = freeSlotRepository.findById(slotId);
////        if (freeSlotOpt.isEmpty()) {
////            logger.error("Slot with ID {} not found.", slotId);
////            return false;
////        }
////
////        FreeSlot freeSlot = freeSlotOpt.get();
////
////        // Проверяем статус слота
////        if (!"Свободно".equals(freeSlot.getStatus())) {
////            logger.warn("Slot with ID {} is not free. Current status: {}", slotId, freeSlot.getStatus());
////            return false;
////        }
////
////        // Проверяем мастера, квалификацию, услугу и клиента
////        Optional<Master> masterOpt = masterRepository.findById(masterId);
////        Optional<Clients> clientOpt = clientsRepository.findById(clientId);
////        Optional<Qualification> qualificationOpt = qualificationRepository.findById(qualificationId);
////        Optional<Service_salon> serviceOpt = serviceSalonRepository.findById(serviceId);
////        Optional<MasterQualificationService> msqOpt = masterQualificationServiceRepository.findById(MasterQualificationServiceId);
////        if (masterOpt.isEmpty() || qualificationOpt.isEmpty() || serviceOpt.isEmpty() || clientOpt.isEmpty() || msqOpt.isEmpty()) {
////            logger.error("Invalid master, qualification, or service ID provided. Master ID: {}, Qualification ID: {}, Service ID: {}, Client ID: {}",
////                    masterId, qualificationId, serviceId, clientId);
////            return false;
////        }
////
////        Master master = masterOpt.get();
////        MasterQualificationService masterQualificationService = msqOpt.get();
////        Qualification qualification = qualificationOpt.get();
////        Service_salon service = serviceOpt.get();
////        Clients client = clientOpt.get();
////
////        // Создаем новую запись Appointment
////        Appointment appointment = new Appointment();
////        appointment.setMaster(master);
////        appointment.setQualification(qualification);
////        appointment.setServiceSalon(service);
////        appointment.setMasterQualificationService(masterQualificationService);
////        appointment.setClient(client);
////
////        // Привязываем слот к записи
////        appointment.setFreeSlot(freeSlot);
////        appointment.setDateTime(LocalDateTime.of(LocalDate.now(), freeSlot.getStartTime()));
////
////        // Обновляем статус слота
////        freeSlot.setStatus("Занято");
////
////        try {
////            // Сохраняем appointment в базе данных
////            appointmentRepository.save(appointment);
////
////            // Обновляем статус слота
////            freeSlotRepository.save(freeSlot);
////
////            logger.info("Successfully booked slot with ID {}", slotId);
////            return true;
////        } catch (Exception e) {
////            logger.error("Error occurred while booking slot with ID {}: {}", slotId, e.getMessage());
////            return false;
////        }
