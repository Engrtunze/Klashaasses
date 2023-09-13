package com.countriesdata.assessment.service;

import com.countriesdata.assessment.dto.CityPopulationData;

import java.util.List;

public interface CityService {
    List<CityPopulationData> getMostPopulatedCities(int cityNumber);
}
