package com.example.controller;

import com.example.controller.EquipmentController;
import com.example.model.Equipment;
import com.example.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EquipmentControllerTest {

    @Mock
    private EquipmentService equipmentService;

    @InjectMocks
    private EquipmentController equipmentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(new Equipment("Проектор А", true, 1));
        equipmentList.add(new Equipment("Проектор Б", false, 2));

        when(equipmentService.getAllEquipment()).thenReturn(equipmentList);

        ResponseEntity<List<Equipment>> responseEntity = equipmentController.getAllEquipment();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(equipmentList, responseEntity.getBody());
    }

    @Test
    public void testGetEquipmentById() {
        Equipment equipment = new Equipment("Проектор А", true, 1);
        equipment.setId(1L);

        when(equipmentService.getEquipmentById(1L)).thenReturn(Optional.of(equipment));

        ResponseEntity<Equipment> responseEntity = equipmentController.getEquipmentById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(equipment, responseEntity.getBody());
    }

    @Test
    public void testCreateEquipment() {
        Equipment equipment = new Equipment("Проектор А", true, 1);
        equipment.setId(1L);

        when(equipmentService.createEquipment(any(Equipment.class))).thenReturn(equipment);

        ResponseEntity<Equipment> responseEntity = equipmentController.createEquipment(equipment);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(equipment, responseEntity.getBody());
    }

    @Test
    public void testUpdateEquipment() {
        Equipment equipment = new Equipment("Проектор А", true, 1);
        equipment.setId(1L);
        Equipment updatedEquipment = new Equipment("Проектор Б", true, 2);
        updatedEquipment.setId(1L);

        when(equipmentService.updateEquipment(eq(1L), any(Equipment.class))).thenReturn(updatedEquipment);

        ResponseEntity<Equipment> responseEntity = equipmentController.updateEquipment(1L, updatedEquipment);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedEquipment, responseEntity.getBody());
    }

    @Test
    public void testDeleteEquipment() {
        ResponseEntity<Void> responseEntity = equipmentController.deleteEquipment(1L);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testGetActiveEquipment() {
        List<Equipment> activeEquipmentList = new ArrayList<>();
        activeEquipmentList.add(new Equipment("Проектор А", true, 1));

        when(equipmentService.findByStatus(true)).thenReturn(activeEquipmentList);

        List<Equipment> response = equipmentController.getActiveEquipment();

        assertEquals(activeEquipmentList, response);
    }

    @Test
    public void testGetInactiveEquipment() {
        List<Equipment> inactiveEquipmentList = new ArrayList<>();
        inactiveEquipmentList.add(new Equipment("Проектор Б", false, 2));

        when(equipmentService.findByStatus(false)).thenReturn(inactiveEquipmentList);

        List<Equipment> response = equipmentController.getInactiveEquipment();

        assertEquals(inactiveEquipmentList, response);
    }
}