package com.countriesdata.assessment.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CountryCurrencyData {
    private String name;
    private String currency;
    private String iso2;
    private String iso3;
}
