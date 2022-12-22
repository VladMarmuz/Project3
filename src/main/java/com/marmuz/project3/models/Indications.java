package com.marmuz.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "indications")
public class Indications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100,max = 100,message = "Value should be between -100 and 100 *C")
    private Double value;
    @Column(name = "raining")
    @NotEmpty(message = "Raining should not be empty")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    @NotEmpty(message = "Sensor should not be empty")
    private Sensor sensor;

    @Column(name = "created_at")
    @NotEmpty
    private LocalDateTime created_at;

    public Indications() {
    }

    public Indications(int id, Double value, boolean raining, Sensor sensor, LocalDateTime created_at) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
