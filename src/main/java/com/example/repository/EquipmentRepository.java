package com.example.repository;

import com.example.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Метод для поиска оборудования по статусу
    List<Equipment> findByStatus(boolean status);

    // Метод для поиска оборудования по типу
    List<Equipment> findByTypeId(Integer typeId);

    // Метод для поиска оборудования по типу и статусу
    List<Equipment> findByTypeIdAndStatus(Integer typeId, boolean status);
}
