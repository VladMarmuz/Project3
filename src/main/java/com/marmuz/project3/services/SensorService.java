package com.marmuz.project3.services;

import com.marmuz.project3.models.Sensor;
import com.marmuz.project3.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void saveSensor(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findSensorByName(String name){
        return sensorRepository.findByName(name);
    }
}
