package com.countriesdata.assessment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class CountryGeneralDetailsDTO {
    private List<PopulationCountData> population;
    private String capitalCity;
    private String location;
    private String currency;
    private String ISO2AND3;
}
