package com.example.model;
public class EquipmentRequest {
    private String name;
    private boolean status;
    private Integer type; // Только ID типа оборудования, а не объект типа

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
