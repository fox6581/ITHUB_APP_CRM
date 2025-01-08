package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WorkHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate workDate;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "month_id")
    private Month month;

    @ManyToMany
    @JoinTable(
            name = "master_workhours",
            joinColumns = @JoinColumn(name = "workhours_id"),
            inverseJoinColumns = @JoinColumn(name = "master_id")
    )
    private List<Master> masters; // Связь "многие ко многим" с мастерами

    @OneToMany(mappedBy = "workHours", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Slot> slots = new ArrayList<>(); // Коллекция слотов

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
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

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    // Метод для генерации слотов
    public void generateSlots() {
        LocalTime currentTime = this.startTime;

        // Пока текущее время меньше времени окончания
        while (currentTime.isBefore(this.endTime)) {
            LocalTime slotEndTime = currentTime.plusMinutes(30);

            if (slotEndTime.isAfter(this.endTime)) {
                break; // Не создаем слот, если он выходит за пределы рабочего времени
            }

            // Создаем слот для каждого мастера
            for (Master master : this.masters) {
                Slot slot = new Slot();
                slot.setStartTime(currentTime);
                slot.setEndTime(slotEndTime);
                slot.setStatus("свободно"); // По умолчанию слот доступен
                slot.setWorkHours(this); // Устанавливаем связь с рабочими часами
                slot.setMaster(master); // Устанавливаем мастера для этого слота

                this.slots.add(slot); // Добавляем слот в список
            }

            // Переходим к следующему слоту
            currentTime = slotEndTime;
        }
    }
}
