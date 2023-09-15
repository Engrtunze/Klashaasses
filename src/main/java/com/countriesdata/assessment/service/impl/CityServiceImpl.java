package com.countriesdata.assessment.service.impl;

import com.countriesdata.assessment.config.ApiClient;
import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.dto.StateCityDTO;
import com.countriesdata.assessment.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final ApiClient apiClient;
    @Override
    public List<CityPopulationData> getMostPopulatedCities(int cityNumber) {
        List<CityPopulationData> citiesPopulationData = apiClient.getCitiesPopulationData();
        List<String> targetCountries = Arrays.asList("Italy", "New Zealand", "Ghana");
        citiesPopulationData = citiesPopulationData.stream()
                .filter(city -> targetCountries.contains(city.getCountry()))
                .collect(Collectors.toList());

        citiesPopulationData.sort((p1,p2) ->
                Integer.compare(Integer.parseInt(p2.getPopulationCounts().get(0).getValue()), Integer.parseInt(p1.getPopulationCounts().get(0).getValue())));
        return citiesPopulationData.subList(0, Math.min(cityNumber, citiesPopulationData.size()));
    }

    @Override
    public List<StateCityDTO> getCitiesOfStates(String country) {
        List<String> states = apiClient.getStates(country);
        List<StateCityDTO> stateCityList = new ArrayList<>();

        for (String state : states) {
            List<String> cities = apiClient.getCities(country, state);
            var stateCity = new StateCityDTO();
            stateCity.setState(state);
            stateCity.setCities(cities);
            stateCityList.add(stateCity);
        }

        return stateCityList;

    }
}
