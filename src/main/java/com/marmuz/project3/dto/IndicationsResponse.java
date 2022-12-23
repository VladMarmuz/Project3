package com.marmuz.project3.dto;

import java.util.List;
import java.util.stream.Stream;

public class IndicationsResponse {
    private List<IndicationsDTO> indications;

    public IndicationsResponse(List<IndicationsDTO> indications) {
        this.indications = indications;
    }

    public List<IndicationsDTO> getIndications() {
        return indications;
    }

    public void setIndications(List<IndicationsDTO> indications) {
        this.indications = indications;
    }
}
