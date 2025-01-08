package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime startTime;
    private LocalTime endTime;
    private String status; // Статус (например, "доступен", "забронирован")

//    private LocalTime bookedTime; // Время записи клиента
    private Double price; // Стоимость услуги
    @ManyToOne
    @JoinColumn(name = "workhours_id")
    private WorkHours workHours; // Связь с рабочими часами

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master; // Связь с мастером

    @ManyToOne
    @JoinColumn(name = "client_id") // Добавляем связь с клиентом
    private Clients clients; // Это новая сущность для хранения информации о клиенте

    @ManyToOne
    @JoinColumn(name = "service_id") // Добавляем связь с услугой
    private Service_salon service; // Связь с услугой

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public WorkHours getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHours workHours) {
        this.workHours = workHours;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

    public Service_salon getService() {
        return service;
    }

    public void setService(Service_salon service) {
        this.service = service;
    }

//    public LocalTime getBookedTime() {
//        return bookedTime;
//    }
//
//    public void setBookedTime(LocalTime bookedTime) {
//        this.bookedTime = bookedTime;
//    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
