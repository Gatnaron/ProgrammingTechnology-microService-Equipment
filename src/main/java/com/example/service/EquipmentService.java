package com.example.service;

import com.example.model.Equipment;
import com.example.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    // Метод для создания оборудования
    public Equipment createEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    // Метод для получения всех оборудований
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // Метод для получения оборудования по его идентификатору
    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    // Метод для обновления информации об оборудовании
    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {
        Equipment equipment = equipmentRepository.findById(id).orElse(null);
        if (equipment != null) {
            equipment.setName(updatedEquipment.getName());
            equipment.setStatus(updatedEquipment.isStatus());
            equipment.setTypeId(updatedEquipment.getTypeId());
            return equipmentRepository.save(equipment);
        }
        return null;
    }

    // Метод для удаления оборудования по его идентификатору
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

    // Метод для поиска оборудования по статусу
    public List<Equipment> findByStatus(boolean status) {
        return equipmentRepository.findByStatus(status);
    }

    // Метод для изменения статуса оборудования
    public Equipment changeStatus(Long id, boolean newStatus) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Оборудование не найдено с id: " + id));
        equipment.setStatus(newStatus);
        return equipmentRepository.save(equipment);
    }

    // Метод для поиска оборудования по типу
    public List<Equipment> getEquipmentByTypeId(Integer typeId) {
        return equipmentRepository.findByTypeId(typeId);
    }

    // Метод для поиска оборудования по типу и статусу
    public List<Equipment> getEquipmentByTypeIdAndStatus(Integer typeId, boolean status) {
        return equipmentRepository.findByTypeIdAndStatus(typeId, status);
    }
}
