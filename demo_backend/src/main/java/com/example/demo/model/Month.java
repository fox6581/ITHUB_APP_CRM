package com.example.demo.model;

import com.example.demo.model.WorkHours;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;



    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }


//    public List<WorkHours> getWorkHours() {
//        return workHours;
//    }
//
//    public void setWorkHours(List<WorkHours> workHours) {
//        this.workHours = workHours;
//    }
}
