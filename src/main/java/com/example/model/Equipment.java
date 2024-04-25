package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name="equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean status;
    @Column(name = "type_id")
    private Integer typeId;

    public Equipment() {}

    public Equipment(String name, boolean status){
        this.name = name;
        this.status = status;
    }

    public Long getId(){
        return id;
    }
    public void setId(long l){
        this.id = id;
    }
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
