package com.example.demo.service;

import com.example.demo.model.Clients;
import com.example.demo.model.Service_salon;
import com.example.demo.model.Slot;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.repository.SlotRepository;
import com.example.demo.repository.ServiceSalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@Service
public class SlotServiceImpl implements SlotService {
    private static final Logger logger = LoggerFactory.getLogger(SlotServiceImpl.class);
    private final SlotRepository slotRepository;
    private final ClientsRepository clientsRepository;
    private final ServiceSalonRepository serviceSalonRepository; // Переименовано

    @Autowired
    public SlotServiceImpl(SlotRepository slotRepository, ClientsRepository clientsRepository, ServiceSalonRepository serviceSalonRepository) {
        this.slotRepository = slotRepository;
        this.clientsRepository = clientsRepository;
        this.serviceSalonRepository = serviceSalonRepository;
    }

    @Override
    public List<Slot> getSlotsByDate(LocalDate date) {
        return slotRepository.findByWorkHours_WorkDate(date);
    }

    @Override
    public List<Slot> getSlotsByDateAndMaster(LocalDate date, Long masterId) {
        return slotRepository.findByWorkHours_WorkDateAndMaster_Id(date, masterId);
    }

    // Реализуйте метод с правильными параметрами
    @Override
    public boolean isSlotAvailable(LocalDate date, Long masterId, LocalTime startTime, LocalTime endTime) {
        List<Slot> slots = slotRepository.findByWorkHours_WorkDateAndMaster_Id(date, masterId);
        for (Slot slot : slots) {
            if (startTime.isBefore(slot.getEndTime()) && endTime.isAfter(slot.getStartTime())) {
                return false; // Слот занят
            }
        }
        return true; // Слот доступен
    }

    // Бронирование слота
    @Override
    public boolean bookSlot(Long slotId, Long clientId, Long serviceId,  Double price) {
        Slot slot = slotRepository.findById(slotId).orElse(null);
        if (slot == null) {
            return false; // Слот не существует
        }

        if (!"свободно".equals(slot.getStatus())) {
            return false; // Слот уже забронирован
        }

        Clients client = clientsRepository.findById(clientId).orElse(null);
        if (client == null) {
            return false; // Клиент не найден
        }

        Service_salon service = serviceSalonRepository.findById(serviceId).orElse(null);
        if (service == null) {
            logger.error("Service not found with ID: {}", serviceId);
            return false; // Услуга не найдена
        }

        // Устанавливаем параметры слота
        slot.setStatus("забронировано");
        slot.setClients(client);
        slot.setService(service);
//        slot.setBookedTime(bookedTime);
        slot.setPrice(price);

        slotRepository.save(slot); // Сохраняем изменения
        logger.info("Slot with ID: {} successfully booked", slotId);
        return true;
    }


    @Override
    public boolean cancelBooking(Long slotId, Long clientId) {
        // Находим слот
        Slot slot = slotRepository.findById(slotId).orElse(null);

        if (slot == null || !"забронировано".equals(slot.getStatus())) {
            return false; // Слот не найден или не забронирован
        }

        // Проверяем, что клиент соответствует бронированию
        if (slot.getClients() == null || !slot.getClients().getId().equals(clientId)) {
            return false; // Клиент не соответствует
        }

        // Сбрасываем статус и данные бронирования
        slot.setStatus("свободно");
        slot.setClients(null);
        slot.setService(null);
//        slot.setBookedTime(null);
        slot.setPrice(null);

        // Сохраняем изменения
        slotRepository.save(slot);

        return true; // Успешная отмена
    }

}
