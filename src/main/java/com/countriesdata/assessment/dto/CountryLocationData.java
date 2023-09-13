package com.countriesdata.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CountryLocationData {
    private String name;
    private String iso2;
    @JsonProperty("long")
    private int longitude;
    @JsonProperty("lat")
    private int latitude;
}
