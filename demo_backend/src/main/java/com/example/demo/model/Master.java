package com.example.demo.model;

import com.example.demo.model.Qualification;
import com.example.demo.model.WorkHours;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Master {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;


    // Связь многие ко многим с WorkHours
    @ManyToMany
    @JoinTable(
            name = "master_workhours",  // Явное название таблицы для связи
            joinColumns = @JoinColumn(name = "master_id"),  // Колонка для связи с Master
            inverseJoinColumns = @JoinColumn(name = "workhours_id") // Колонка для связи с WorkHours
    )
    @JsonBackReference // предотвращает циклическую зависимость с WorkHours
    private List<WorkHours> workHours;
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



    public List<WorkHours> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkHours> workHours) {
        this.workHours = workHours;
    }
}
