package com.example.controller;

import com.example.model.Equipment;
import com.example.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    // Endpoint для создания нового оборудования
    @PostMapping("/create")
    public ResponseEntity<Equipment> createEquipment(@RequestBody Equipment equipment) {
        Equipment createdEquipment = equipmentService.createEquipment(equipment);
        return new ResponseEntity<>(createdEquipment, HttpStatus.CREATED);
    }

    // Endpoint для получения всех оборудований
    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        List<Equipment> allEquipment = equipmentService.getAllEquipment();
        return new ResponseEntity<>(allEquipment, HttpStatus.OK);
    }

    // Endpoint для получения оборудования по его идентификатору
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Optional<Equipment> equipment = equipmentService.getEquipmentById(id);
        return equipment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint для обновления информации об оборудовании
    @PutMapping("/update/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        Equipment equipment = equipmentService.updateEquipment(id, updatedEquipment);
        if (equipment != null) {
            return new ResponseEntity<>(equipment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint для удаления оборудования по его идентификатору
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Отображение оборудования только со статусом true
    @GetMapping("/active")
    public List<Equipment> getActiveEquipment() {
        return equipmentService.findByStatus(true);
    }

    // Отображение оборудования только со статусом false
    @GetMapping("/inactive")
    public List<Equipment> getInactiveEquipment() {
        return equipmentService.findByStatus(false);
    }

    // "Использовать оборудование" - меняющий статус оборудования с true на false
    @PutMapping("/use/{id}")
    public Equipment useEquipment(@PathVariable Long id) {
        return equipmentService.changeStatus(id, false);
    }

    // "Освободить оборудование" - меняющий статус оборудования с false на true
    @PutMapping("/release/{id}")
    public Equipment releaseEquipment(@PathVariable Long id) {
        return equipmentService.changeStatus(id, true);
    }

    @GetMapping("/type/{typeId}")
    public ResponseEntity<List<Equipment>> getEquipmentByType(@PathVariable Integer typeId) {
        List<Equipment> equipment = equipmentService.getEquipmentByTypeId(typeId);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @GetMapping("/type/{typeId}/status/{status}")
    public ResponseEntity<List<Equipment>> getEquipmentByTypeAndStatus(
            @PathVariable Integer typeId, @PathVariable boolean status) {
        List<Equipment> equipment = equipmentService.getEquipmentByTypeIdAndStatus(typeId, status);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }
}
