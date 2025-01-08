//package com.example.demo.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import org.antlr.v4.runtime.misc.NotNull;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//public class Appointment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "client_id", nullable = false)
//    private Clients client;  // Связь с клиентом
//
//    // Связь с мастером (обязательно)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "master_id", nullable = false)
//    @NotNull
//    private Master master;
//
//    // Связь с услугой (обязательно)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "service_id", nullable = false)
//    @NotNull
//    private Service_salon serviceSalon;  // Связь с услугой
//
//    // Связь с квалификацией мастера (обязательно)
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "qualification_id", nullable = false)
//    @NotNull
//    private Qualification qualification;
//
//
//    @ManyToOne
//    @JoinColumn(name = "master_qualification_service_id", nullable = false)
//    private MasterQualificationService masterQualificationService;  // Связь с мастером, квалификацией и услугой
//
//
//    private LocalDateTime dateTime;  // Дата и время записи
//
//    @ManyToOne
//    @JoinColumn(name = "free_slot_id")  // Связь с таблицей FreeSlot
//    private FreeSlot freeSlot;  // Связь со слотом
//
//
//    // Геттеры и сеттеры
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Clients getClient() {
//        return client;
//    }
//
//    public void setClient(Clients client) {
//        this.client = client;
//    }
//
//    public Master getMaster() {
//        return master;
//    }
//
//    public void setMaster(Master master) {
//        this.master = master;
//    }
//
//
//    public LocalDateTime getDateTime() {
//        return dateTime;
//    }
//
//    public void setDateTime(LocalDateTime dateTime) {
//        this.dateTime = dateTime;
//    }
//
//
//    public FreeSlot getFreeSlot() {
//        return freeSlot;
//    }
//
//    public void setFreeSlot(FreeSlot freeSlot) {
//        this.freeSlot = freeSlot;
//    }
//
//    public MasterQualificationService getMasterQualificationService() {
//        return masterQualificationService;
//    }
//
//    public void setMasterQualificationService(MasterQualificationService masterQualificationService) {
//        this.masterQualificationService = masterQualificationService;
//    }
//
//
//
//    public Service_salon getServiceSalon() {
//        return serviceSalon;
//    }
//
//    public void setServiceSalon(Service_salon serviceSalon) {
//        this.serviceSalon = serviceSalon;
//    }
//
//    public Qualification getQualification() {
//        return qualification;
//    }
//
//    public void setQualification(Qualification qualification) {
//        this.qualification = qualification;
//    }
//
//}
