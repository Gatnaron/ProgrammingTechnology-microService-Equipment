package com.example.controller;

import com.example.model.EquipmentUsage;
import com.example.service.EquipmentUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquipmentUsageController {

    @Autowired
    private EquipmentUsageService equipmentUsageService;

    @GetMapping("/equipment-usage")
    public ResponseEntity<List<EquipmentUsage>> getAllEquipmentUsage() {
        List<EquipmentUsage> equipmentUsageList = equipmentUsageService.getAllEquipmentUsage();
        return new ResponseEntity<>(equipmentUsageList, HttpStatus.OK);
    }

    @PostMapping("/equipment-usage/add")
    public ResponseEntity<EquipmentUsage> addEquipmentUsage(@RequestBody EquipmentUsage equipmentUsage) {
        EquipmentUsage addedEquipmentUsage = equipmentUsageService.addEquipmentUsage(equipmentUsage);
        if (addedEquipmentUsage != null) {
            return new ResponseEntity<>(addedEquipmentUsage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/equipment-usage/del/{id}")
    public ResponseEntity<Void> deleteEquipmentUsage(@PathVariable Long id) {
        equipmentUsageService.deleteEquipmentUsage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
