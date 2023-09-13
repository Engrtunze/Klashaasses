package com.countriesdata.assessment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CountryPopulationData {
    private  String country;
    private  String code;
    private  String iso3;
    private List<PopulationCountData> populationCounts;
}
