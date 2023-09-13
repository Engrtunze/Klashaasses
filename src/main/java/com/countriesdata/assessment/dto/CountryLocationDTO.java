package com.countriesdata.assessment.dto;

import lombok.Data;

@Data
public class CountryLocationDTO {
    private String name;
    private String iso2;
    private int longitude;
    private int lat;
}
