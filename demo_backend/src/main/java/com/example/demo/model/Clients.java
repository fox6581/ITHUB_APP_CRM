package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String name;
    private String phoneNumber;
    private String email;  // Добавьте поле email


//    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Appointment> appointments = new ArrayList<>();

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
