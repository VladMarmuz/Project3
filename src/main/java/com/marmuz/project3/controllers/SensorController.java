package com.marmuz.project3.controllers;

import com.marmuz.project3.dto.SensorDTO;
import com.marmuz.project3.models.Sensor;
import com.marmuz.project3.services.SensorService;
import com.marmuz.project3.util.SensorErrorResponse;
import com.marmuz.project3.util.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper mapper;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper mapper) {
        this.sensorService = sensorService;
        this.mapper = mapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fields = bindingResult.getFieldErrors();
            for (FieldError error : fields) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMessage.toString());
        }
        sensorService.saveSensor(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public Sensor convertToSensor(SensorDTO sensorDTO) {
        return mapper.map(sensorDTO, Sensor.class);
    }


}
