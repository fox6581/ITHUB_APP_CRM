package com.example.demo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;


@Entity
public class Service_salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal basicPrice;
    private Duration basicTime; // Время как Duration


    // Связь с объединяющей таблицей MasterQualificationService
    @OneToMany(mappedBy = "service")
    private List<MasterQualificationService> masterQualificationServices;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasicPrice() {
        return basicPrice;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    public Duration getBasicTime() {
        return basicTime;
    }

    public void setBasicTime(Duration basicTime) {
        this.basicTime = basicTime;
    }
}
