package com.example.demo.model;

import jakarta.persistence.*;


@Entity
public class MasterQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master; // Связь с мастером

    @ManyToOne
    @JoinColumn(name = "qualification_id")
    private Qualification qualification; // Связь с квалификацией



    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Qualification getQualification() {
        return qualification;
    }


    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }


}
