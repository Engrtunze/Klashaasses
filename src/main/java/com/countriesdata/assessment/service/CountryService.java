package com.countriesdata.assessment.service;

import com.countriesdata.assessment.dto.CountryCurrencyConversionDTO;
import com.countriesdata.assessment.dto.CountryCurrencyConversionRequest;
import com.countriesdata.assessment.dto.CountryGeneralDetailsDTO;

public interface CountryService {
CountryGeneralDetailsDTO getCountryDetails(String country);
CountryCurrencyConversionDTO covertCurrency(CountryCurrencyConversionRequest request);
}
