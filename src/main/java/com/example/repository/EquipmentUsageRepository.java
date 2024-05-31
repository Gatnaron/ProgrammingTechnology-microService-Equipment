package com.example.repository;

import com.example.model.EquipmentUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentUsageRepository extends JpaRepository<EquipmentUsage, Long> {
}