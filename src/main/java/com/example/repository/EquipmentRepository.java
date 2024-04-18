package com.example.repository;

import com.example.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    // Метод для поиска оборудования по статусу
    List<Equipment> findByStatus(boolean status);
}
