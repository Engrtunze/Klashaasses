package com.countriesdata.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class CountryCurrencyConversionDTO {
    private String countryCurrency;
    private BigDecimal convertedCurrencyValue;
}
