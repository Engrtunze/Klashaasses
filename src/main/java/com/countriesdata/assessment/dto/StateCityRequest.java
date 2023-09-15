package com.countriesdata.assessment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Data
public class StateCityRequest {
    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;
}
