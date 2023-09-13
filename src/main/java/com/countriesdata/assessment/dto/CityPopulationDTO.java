package com.countriesdata.assessment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CityPopulationDTO {
    private String city;
    private  String country;
    private List<PopulationCountDTO> populationCounts;
}
