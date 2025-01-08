package com.example.demo.controller;

import com.example.demo.model.Slot;
import com.example.demo.model.WorkHours;
import com.example.demo.service.SlotService;
import com.example.demo.service.WorkHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/workhours")
public class WorkHoursController {

    private final WorkHoursService workHoursService;
    private final SlotService slotService;

    @Autowired
    public WorkHoursController(WorkHoursService workHoursService, SlotService slotService) {
        this.workHoursService = workHoursService;
        this.slotService = slotService;
    }

    // Получить все рабочие часы
    @GetMapping
    public List<WorkHours> getAllWorkHours() {
        return workHoursService.getAllWorkHours();
    }

    // Получить рабочие часы по ID
    @GetMapping("/{id}")
    public WorkHours getWorkHoursById(@PathVariable Long id) {
        return workHoursService.getWorkHoursById(id)
                .orElseThrow(() -> new IllegalArgumentException("WorkHours with ID " + id + " not found"));
    }

    // Добавить рабочие часы
    @PostMapping
    public WorkHours addWorkHours(@RequestBody WorkHours workHours) {
        System.out.println("Received request to add WorkHours: " + workHours);
        return workHoursService.addWorkHours(workHours);
    }

    // Обновить рабочие часы
    @PutMapping("/{id}")
    public WorkHours updateWorkHours(@PathVariable Long id, @RequestBody WorkHours updatedWorkHours) {
        return workHoursService.updateWorkHours(id, updatedWorkHours);
    }

    // Удалить рабочие часы
    @DeleteMapping("/{id}")
    public void deleteWorkHours(@PathVariable Long id) {
        workHoursService.deleteWorkHours(id);
    }

    // Получить рабочие часы по дате
    @GetMapping("/date/{workDate}")
    public List<WorkHours> getWorkHoursByDate(@PathVariable String workDate) {
        return workHoursService.getWorkHoursByDate(LocalDate.parse(workDate));
    }

    // Получить слоты по дате
    @GetMapping("/date/{workDate}/slots")
    public List<Slot> getSlotsByDate(@PathVariable String workDate) {
        return slotService.getSlotsByDate(LocalDate.parse(workDate));
    }

    // Получить слоты по дате и мастеру
    @GetMapping("/date/{workDate}/slots/master/{masterId}")
    public List<Slot> getSlotsByDateAndMaster(@PathVariable String workDate, @PathVariable Long masterId) {
        return slotService.getSlotsByDateAndMaster(LocalDate.parse(workDate), masterId);
    }
}
