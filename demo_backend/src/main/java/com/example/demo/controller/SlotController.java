package com.example.demo.controller;

import com.example.demo.model.BookedSlot;
import com.example.demo.model.Clients;
import com.example.demo.repository.ClientsRepository;
import com.example.demo.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalTime;

@RestController
@RequestMapping("/slots")
public class SlotController {

    private final SlotService slotService;
    private final ClientsRepository clientsRepository;

    @Autowired
    public SlotController(SlotService slotService, ClientsRepository clientsRepository) {
        this.slotService = slotService;
        this.clientsRepository = clientsRepository;
    }

    @PostMapping("/book/{slotIds}")
    public String bookSlots(@PathVariable String slotIds, @RequestBody BookedSlot request) {
        Logger logger = LoggerFactory.getLogger(getClass());

        logger.info("Received booking request for slotIds: {}", slotIds);
        logger.info("Client name: {}", request.getClientName());
        logger.info("Client phone: {}", request.getClientPhone());
        logger.info("Client email: {}", request.getClientEmail());
        logger.info("Service ID: {}", request.getServiceId());
//        logger.info("Booked Time: {}", request.getBookedTime());
        logger.info("Price: {}", request.getPrice());

        String name = request.getClientName();
        String phone = request.getClientPhone();
        String email = request.getClientEmail();

        Clients client = clientsRepository.findByPhoneNumber(phone).orElse(null);

        if (client == null) {
            logger.info("Client not found, creating new client...");
            Clients newClient = new Clients();
            newClient.setName(name);
            newClient.setPhoneNumber(phone);
            newClient.setEmail(email);
            client = clientsRepository.save(newClient);
        }

        String[] slotIdArray = slotIds.split(",");
        boolean allSlotsBooked = true;

        for (String slotIdStr : slotIdArray) {
            Long slotId = Long.valueOf(slotIdStr);
            boolean isBooked = slotService.bookSlot(
                    slotId,
                    client.getId(),
                    request.getServiceId(),
//                    request.getBookedTime(),
                    request.getPrice()
            );

            if (isBooked) {
                logger.info("Slot {} successfully booked for client {}", slotId, client.getName());
            } else {
                logger.error("Failed to book slot {} for client {}", slotId, client.getName());
                allSlotsBooked = false;
            }
        }

        if (allSlotsBooked) {
            return "All slots successfully booked!";
        } else {
            return "Some slots could not be booked.";
        }
    }


    // Метод для отмены бронирования
    @PutMapping("/cancel/{slotId}/{clientId}")
    public String cancelBooking(@PathVariable Long slotId, @PathVariable Long clientId) {
        Logger logger = LoggerFactory.getLogger(getClass());

        boolean isCancelled = slotService.cancelBooking(slotId, clientId);

        if (isCancelled) {
            logger.info("Slot {} successfully cancelled for client {}", slotId, clientId);
            return "Booking cancelled successfully!";
        } else {
            logger.error("Failed to cancel slot {} for client {}", slotId, clientId);
            return "Failed to cancel booking. Slot may not be booked or client mismatch.";
        }
    }

}


