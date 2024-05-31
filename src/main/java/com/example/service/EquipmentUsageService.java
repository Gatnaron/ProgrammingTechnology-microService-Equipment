package com.example.service;

import com.example.model.Equipment;
import com.example.model.EquipmentUsage;
import com.example.repository.EquipmentUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentUsageService {

    @Autowired
    private EquipmentUsageRepository equipmentUsageRepository;

    @Autowired
    private EquipmentService equipmentService;

    public List<EquipmentUsage> getAllEquipmentUsage() {
        return equipmentUsageRepository.findAll();
    }

    public EquipmentUsage addEquipmentUsage(EquipmentUsage equipmentUsage) {
        Equipment equipment = equipmentService.getEquipmentById(equipmentUsage.getEquipmentId()).orElse(null);
        if (equipment != null) {
            equipment.setStatus(false);
            equipmentService.updateEquipment(equipment.getId(), equipment);
            return equipmentUsageRepository.save(equipmentUsage);
        }
        return null;
    }

    public void deleteEquipmentUsage(Long id) {
        EquipmentUsage equipmentUsage = equipmentUsageRepository.findById(id).orElse(null);
        if (equipmentUsage != null) {
            equipmentUsageRepository.deleteById(id);
            Equipment equipment = equipmentService.getEquipmentById(equipmentUsage.getEquipmentId()).orElse(null);
            if (equipment != null) {
                equipment.setStatus(true);
                equipmentService.updateEquipment(equipment.getId(), equipment);
            }
        }
    }
}
