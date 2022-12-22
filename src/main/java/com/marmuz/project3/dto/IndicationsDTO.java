package com.marmuz.project3.dto;

import com.marmuz.project3.models.Sensor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class IndicationsDTO {

    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100,max = 100,message = "Value should be between -100 and 100 *C")
    private Double value;

    @NotEmpty(message = "Raining should not be empty")
    private boolean raining;

    @NotEmpty(message = "Sensor should not be empty")
    private Sensor sensor;

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
}
