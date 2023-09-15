package com.countriesdata.assessment.service;

import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.dto.StateCityDTO;

import java.util.List;

public interface CityService {
    List<CityPopulationData> getMostPopulatedCities(int cityNumber);

   List<StateCityDTO> getCitiesOfStates(String country);
}
