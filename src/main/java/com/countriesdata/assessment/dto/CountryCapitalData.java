package com.countriesdata.assessment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CountryCapitalData {
    private String name;
    private String capital;
    private String iso2;
    private String iso3;
}
