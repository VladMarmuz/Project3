package com.marmuz.project3.services;

import com.marmuz.project3.models.Indications;
import com.marmuz.project3.repositories.IndicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class IndicationsService {
    private final IndicationsRepository indicationsRepository;
    private final SensorService sensorService;

    @Autowired
    public IndicationsService(IndicationsRepository indicationsRepository, SensorService sensorService) {
        this.indicationsRepository = indicationsRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void addIndications(Indications indications) {
       enrichIndications(indications);
        indicationsRepository.save(indications);

    }

    private void enrichIndications(Indications indications) {
        indications.setSensor(sensorService.findSensorByName(indications.getSensor().getName()).get());
        indications.setCreated_at(LocalDateTime.now());
    }

    public List<Indications> findAll() {
        return indicationsRepository.findAll();
    }

    public int findRainyDaysCount() {
        return 0;
    }
}
