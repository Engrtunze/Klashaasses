package com.countriesdata.assessment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CityPopulationResponse {
    private boolean error;
    private  String msg;
    private List<CityPopulationDTO> data;
}
