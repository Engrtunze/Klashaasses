package com.countriesdata.assessment.service;

import com.countriesdata.assessment.dto.CountryGeneralDetailsDTO;

public interface CountryService {
CountryGeneralDetailsDTO getCountryDetails(String country);
}
