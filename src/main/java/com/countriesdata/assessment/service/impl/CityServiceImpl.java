package com.countriesdata.assessment.service.impl;

import com.countriesdata.assessment.config.ApiClient;
import com.countriesdata.assessment.dto.CityPopulationDTO;
import com.countriesdata.assessment.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final ApiClient apiClient;
    @Override
    public List<CityPopulationDTO> getMostPopulatedCities(int cityNumber) {
        List<CityPopulationDTO> citiesPopulationData = apiClient.getCitiesPopulationData();
        List<String> targetCountries = Arrays.asList("Italy", "New Zealand", "Ghana");
        citiesPopulationData = citiesPopulationData.stream()
                .filter(city -> targetCountries.contains(city.getCountry()))
                .collect(Collectors.toList());

        citiesPopulationData.sort((p1,p2) ->
                Integer.compare(Integer.parseInt(p2.getPopulationCounts().get(0).getValue()), Integer.parseInt(p1.getPopulationCounts().get(0).getValue())));
        return citiesPopulationData.subList(0, Math.min(cityNumber, citiesPopulationData.size()));
    }
}
