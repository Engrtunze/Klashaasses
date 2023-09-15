package com.countriesdata.assessment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CountryCurrencyConversionRequest {
    @NotBlank
    private String country;
    @NotNull
    private BigDecimal amount;
    @NotBlank
    private String targetCurrency;
}
