package com.marmuz.project3.controllers;

import com.marmuz.project3.dto.IndicationsDTO;
import com.marmuz.project3.dto.IndicationsResponse;
import com.marmuz.project3.models.Indications;
import com.marmuz.project3.services.IndicationsService;
import com.marmuz.project3.util.ErrorResponse;
import com.marmuz.project3.util.IndicationsNotAddedException;
import com.marmuz.project3.util.validator.IndicationsValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/indications")
public class IndicationsController {
    private final IndicationsService indicationsService;
    private final IndicationsValidator validator;
    private final ModelMapper mapper;

    @Autowired
    public IndicationsController(IndicationsService indicationsService, IndicationsValidator validator, ModelMapper mapper) {
        this.indicationsService = indicationsService;
        this.validator = validator;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addIndications(@RequestBody @Valid IndicationsDTO indicationsDTO
            , BindingResult bindingResult) {
        Indications indications = convertToIndications(indicationsDTO);
        validator.validate(indications, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder responseMessage = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                responseMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                        .append(";");
            }
            throw new IndicationsNotAddedException(responseMessage.toString());
        }

        indicationsService.addIndications(indications);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/rainyDaysCount")
    public Long rainyDaysCount(){
        return indicationsService.findAll().stream().filter(Indications::isRaining).count();
    }

    @GetMapping("")
    public IndicationsResponse getAllIndications(){
        return new IndicationsResponse(indicationsService.findAll().stream()
                .map(this::convertToIndicationsDTO)
                .collect(Collectors.toList()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(IndicationsNotAddedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public IndicationsDTO convertToIndicationsDTO(Indications indications){
        return mapper.map(indications,IndicationsDTO.class);
    }
    public Indications convertToIndications(IndicationsDTO indicationsDTO) {
        return mapper.map(indicationsDTO, Indications.class);
    }
}
