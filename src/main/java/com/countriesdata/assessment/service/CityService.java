package com.countriesdata.assessment.service;

import com.countriesdata.assessment.dto.CityPopulationDTO;

import java.util.List;

public interface CityService {
    List<CityPopulationDTO> getMostPopulatedCities(int cityNumber);
}
