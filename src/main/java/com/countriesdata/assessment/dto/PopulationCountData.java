package com.countriesdata.assessment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PopulationCountData {
    private String year;
    private String value;
    private String sex = ""; // Default to an empty string
    private String reliabilty = ""; // Default to an empty string
}
