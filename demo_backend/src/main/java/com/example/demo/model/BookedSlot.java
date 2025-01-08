package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class BookedSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime time;
    private String clientName;
    private Long clientId;
    private String estimatedTime;
    private String clientEmail;

    private String clientPhone;

    private Long serviceId; // ID услуги
    private LocalTime bookedTime; // Время бронирования
    private Double price; // Стоимость



    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    private String startTime; // время начала
    private String endTime;   // время окончания
    @ManyToOne
    @JoinColumn(name = "workhours_id")  // Ссылается на таблицу WorkHours
    private WorkHours workhours;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getClientName() {
        return clientName;
    }
    public Long getClientId() {
        return clientId;
    }
    // Геттер для startTime
    public String getStartTime() {
        return startTime;
    }

    // Сеттер для startTime
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    // Геттер для endTime
    public String getEndTime() {
        return endTime;
    }

    // Сеттер для endTime
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public WorkHours getWorkhours() {
        return workhours;
    }

    public void setWorkhours(WorkHours workhours) {
        this.workhours = workhours;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getClientEmail() { // Геттер для email
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) { // Сеттер для email
        this.clientEmail = clientEmail;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalTime getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(LocalTime bookedTime) {
        this.bookedTime = bookedTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
