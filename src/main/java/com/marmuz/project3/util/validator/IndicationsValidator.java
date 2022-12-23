package com.marmuz.project3.util.validator;

import com.marmuz.project3.models.Indications;
import com.marmuz.project3.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class IndicationsValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public IndicationsValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Indications.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Indications indications= (Indications) target;
        if(indications.getSensor() == null){
            return;
        }
        if(sensorService.findSensorByName(indications.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor",
                    "Please check your input Sensor. This kind of sensor doesn't exist");
        }

    }
}
