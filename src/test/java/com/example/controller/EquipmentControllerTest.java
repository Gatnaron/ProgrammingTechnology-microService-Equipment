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

    @BeforeEach
    public void setup() {
        testEquipment = new Equipment("Test Equipment", true);
        testEquipment.setId(1L);
    }

    @Test
    public void testCreateEquipment() {
        when(equipmentService.createEquipment(any(Equipment.class))).thenReturn(testEquipment);
        ResponseEntity<Equipment> response = equipmentController.createEquipment(testEquipment);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testEquipment, response.getBody());
    }

    @Test
    public void testUpdateEquipment() {
        when(equipmentService.updateEquipment(eq(1L), any(Equipment.class))).thenReturn(testEquipment);
        ResponseEntity<Equipment> response = equipmentController.updateEquipment(1L, testEquipment);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testEquipment, response.getBody());
    }

    @Test
    public void testGetEquipmentById() {
        when(equipmentService.getEquipmentById(eq(1L))).thenReturn(Optional.of(testEquipment));
        ResponseEntity<Equipment> response = equipmentController.getEquipmentById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testEquipment, response.getBody());
    }

    @Test
    public void testGetAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList.add(testEquipment);
        when(equipmentService.getAllEquipment()).thenReturn(equipmentList);
        ResponseEntity<List<Equipment>> response = equipmentController.getAllEquipment();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(equipmentList, response.getBody());
    }

    @Test
    public void testDeleteEquipment() {
        ResponseEntity<Void> response = equipmentController.deleteEquipment(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}