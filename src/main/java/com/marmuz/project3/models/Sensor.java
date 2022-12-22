package com.marmuz.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Size(min = 3,max = 30, message = "Sensor name should be between 3 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @OneToMany(mappedBy = "sensor")
    private List<Indications> indications;

    public Sensor() {
    }

    public Sensor(int id, String name, List<Indications> indications) {
        this.id = id;
        this.name = name;
        this.indications = indications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Indications> getIndications() {
        return indications;
    }

    public void setIndications(List<Indications> indications) {
        this.indications = indications;
    }
}
