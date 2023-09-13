package com.countriesdata.assessment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PopulationCountDTO {
    private String year;
    private String value;
    private String sex;
    private String reliabilty;
}
