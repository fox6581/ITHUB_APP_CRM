package com.example.demo.model;

import com.example.demo.repository.MasterQualificationServiceRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Table(name = "master_qualification_service")
public class MasterQualificationService {

    private static final Logger logger = Logger.getLogger(MasterQualificationService.class.getName());


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_qualification_id")
    private MasterQualification masterQualification; // Связь с мастером

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service_salon service; // Связь с услугой



    private BigDecimal price; // Цена для конкретной комбинации
    private String estimatedTime; // Время выполнения для конкретной комбинации



    public void calculatePriceAndTime() {
        logger.info("Starting calculation of price and time");
        logger.info("Starting calculation of price and time" +masterQualification);
        if (masterQualification != null && masterQualification.getQualification() != null) {
            // Получение коэффициентов
            BigDecimal priceCoefficient = masterQualification.getQualification().getPrice();
            Long timeCoefficient = masterQualification.getQualification().getTime();

            logger.info("Price Coefficient: " + priceCoefficient);
            logger.info("Time Coefficient: " + timeCoefficient);

            // Базовая цена и время услуги
            BigDecimal basePrice = service.getBasicPrice();
            Duration baseTime = service.getBasicTime();

            logger.info("Base Price: " + basePrice);
            logger.info("Base Time: " + baseTime);

            // Расчет итоговой цены
            this.price = basePrice.multiply(priceCoefficient);
            logger.info("Calculated Price: " + this.price);

            // Расчет итогового времени
            long finalTimeInSeconds = baseTime.getSeconds() * timeCoefficient;
            this.estimatedTime = Duration.ofSeconds(finalTimeInSeconds).toString();
            logger.info("Calculated Estimated Time: " + this.estimatedTime);

        } else {
            // Логика на случай, если квалификация или квалификация мастера не задана
            logger.warning("Master qualification or qualification is not set");
            this.price = BigDecimal.ZERO;
            this.estimatedTime = "0s"; // или другой дефолтный вариант
        }

        logger.info("Calculation completed");
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MasterQualification getMasterQualification() {
        return masterQualification;
    }

    public void setMasterQualification(MasterQualification masterQualification) {
        this.masterQualification = masterQualification;
    }

    public Service_salon getService() {
        return service;
    }

    public void setService(Service_salon service) {
        this.service = service;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
