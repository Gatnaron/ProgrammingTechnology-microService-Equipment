package com.example.service;

import com.example.model.Equipment;
import com.example.repository.EquipmentRepository;
import com.example.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EquipmentServiceTest {

    @Mock
    private EquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    private Equipment equipment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        equipment = new Equipment();
        equipment.setId(1L);
        equipment.setName("Test Equipment");
        equipment.setStatus(true);
        equipment.setTypeId(1);
    }

    @Test
    public void testCreateEquipment() {
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipment);

        Equipment createdEquipment = equipmentService.createEquipment(equipment);

        assertEquals(equipment.getName(), createdEquipment.getName());
        assertEquals(equipment.isStatus(), createdEquipment.isStatus());
        assertEquals(equipment.getTypeId(), createdEquipment.getTypeId());
        verify(equipmentRepository, times(1)).save(equipment);
    }

    @Test
    public void testUpdateEquipment() {
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipment);

        equipment.setName("Updated Equipment");
        equipment.setStatus(false);
        equipment.setTypeId(2);
        Equipment updatedEquipment = equipmentService.updateEquipment(1L, equipment);

        assertEquals("Updated Equipment", updatedEquipment.getName());
        assertEquals(false, updatedEquipment.isStatus());
        assertEquals(2, updatedEquipment.getTypeId());
        verify(equipmentRepository, times(1)).save(equipment);
    }

    @Test
    public void testDeleteEquipment() {
        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));
        doNothing().when(equipmentRepository).deleteById(1L);

        equipmentService.deleteEquipment(1L);

        verify(equipmentRepository, times(1)).deleteById(1L);
    }
}
