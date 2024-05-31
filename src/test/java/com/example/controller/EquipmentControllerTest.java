package com.example.controller;

import com.example.controller.EquipmentController;
import com.example.model.Equipment;
import com.example.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

    private Equipment testEquipment;
    private Equipment updatedEquipment;
    private Equipment inactiveTestEquipment;

    @BeforeEach
    public void setup() {
        testEquipment = new Equipment("Проектор А", true);
        testEquipment.setId(1L);

        updatedEquipment = new Equipment("Проектор Б", true);
        updatedEquipment.setId(1L);

        inactiveTestEquipment = new Equipment("Неактивное оборудование", false);
        inactiveTestEquipment.setId(2L);
    }

    // Тест для метода getActiveEquipment()
    @Test
    public void testGetActiveEquipment() {
        List<Equipment> activeEquipmentList = new ArrayList<>();
        activeEquipmentList.add(testEquipment);

        when(equipmentService.findByStatus(true)).thenReturn(activeEquipmentList);

        List<Equipment> response = equipmentController.getActiveEquipment();

        assertEquals(activeEquipmentList, response);
        assertEquals(1, response.size());
        assertEquals("Проектор А", response.get(0).getName());
        assertEquals(true, response.get(0).getActive());
    }

    // Тест для метода getInactiveEquipment()
    @Test
    public void testGetInactiveEquipment() {
        List<Equipment> inactiveEquipmentList = new ArrayList<>();
        inactiveEquipmentList.add(inactiveTestEquipment);

        when(equipmentService.findByStatus(false)).thenReturn(inactiveEquipmentList);

        List<Equipment> response = equipmentController.getInactiveEquipment();

        assertEquals(inactiveEquipmentList, response);
        assertEquals(1, response.size());
        assertEquals("Неактивное оборудование", response.get(0).getName());
        assertEquals(false, response.get(0).getActive());
    }

    // Тест для метода updateEquipment()
    @Test
    public void testUpdateEquipment() {
        when(equipmentService.updateEquipment(eq(1L), any(Equipment.class))).thenReturn(updatedEquipment);

        Equipment updatedEquipment = new Equipment("Проектор Б", true);
        updatedEquipment.setId(1L);

        ResponseEntity<Equipment> response = equipmentController.updateEquipment(1L, updatedEquipment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Проектор Б", response.getBody().getName());
        assertEquals(true, response.getBody().getActive());
    }

    // Тест для метода createEquipment()
    @Test
    public void testCreateEquipment() {
        when(equipmentService.createEquipment(any(Equipment.class))).thenReturn(testEquipment);

        ResponseEntity<Equipment> response = equipmentController.createEquipment(testEquipment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Проектор А", response.getBody().getName());
        assertEquals(true, response.getBody().getActive());

    }
}