package com.marmuz.project3.services;

import com.marmuz.project3.repositories.IndicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IndicationsService {
    private final IndicationsRepository indicationsRepository;

    @Autowired
    public IndicationsService(IndicationsRepository indicationsRepository) {
        this.indicationsRepository = indicationsRepository;
    }
}
