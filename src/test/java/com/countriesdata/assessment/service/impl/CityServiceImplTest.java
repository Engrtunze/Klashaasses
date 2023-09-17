package com.countriesdata.assessment.service.impl;

import com.countriesdata.assessment.config.ApiClient;
import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.dto.PopulationCountData;
import com.countriesdata.assessment.dto.StateCityDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


 class CityServiceImplTest {
    @Mock
    private ApiClient apiClient;
    private CityServiceImpl cityService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
       cityService = new CityServiceImpl(apiClient);
    }

    @Test
    void testGetMostPopulatedCities() {
        List<CityPopulationData> sampleData = new ArrayList<>();

        var count1 = new PopulationCountData();
        count1.setYear("2013");
        count1.setValue("11370");
        count1.setSex("Both Sexes");
        count1.setReliabilty("Final figure, complete");

        var count2 = new PopulationCountData();
        count1.setYear("2012");
        count1.setValue("11304");
        count1.setSex("Both Sexes");
        count1.setReliabilty("Final figure, complete");


        CityPopulationData city1 = new CityPopulationData();
        city1.setCity("City1");
        city1.setCountry("Italy");
        city1.setPopulationCounts(Arrays.asList(count1, count2));

        CityPopulationData city2 = new CityPopulationData();
        city2.setCity("City2");
        city2.setCountry("New Zealand");
        city2.setPopulationCounts(Arrays.asList(count1, count2));

        sampleData.add(city1);
        sampleData.add(city2);

        when(apiClient.getCitiesPopulationData()).thenReturn(sampleData);

        List<CityPopulationData> result = cityService.getMostPopulatedCities(2);

        System.out.println("Sample Data: " + sampleData);
        System.out.println("Filtered and Sorted Data: " + result);

        assertEquals(2, result.size());
    }

    @Test
    void testGetCitiesOfStates() {
        String country = "Ghana";
        List<String> states = new ArrayList<>();
        states.add("State1");
        states.add("State2");

        when(apiClient.getStates(country)).thenReturn(states);

        List<String> citiesState1 = new ArrayList<>();
        citiesState1.add("City1");
        citiesState1.add("City2");
        List<String> citiesState2 = new ArrayList<>();
        citiesState2.add("City3");

        when(apiClient.getCities(country, "State1")).thenReturn(citiesState1);
        when(apiClient.getCities(country, "State2")).thenReturn(citiesState2);

        List<StateCityDTO> result = cityService.getCitiesOfStates(country);

        List<StateCityDTO> expectedStateCityList = new ArrayList<>();
        StateCityDTO stateCity1 = new StateCityDTO();
        stateCity1.setState("State1");
        stateCity1.setCities(citiesState1);
        StateCityDTO stateCity2 = new StateCityDTO();
        stateCity2.setState("State2");
        stateCity2.setCities(citiesState2);
        expectedStateCityList.add(stateCity1);
        expectedStateCityList.add(stateCity2);

        assertEquals(expectedStateCityList.size(), result.size());
        for (int i = 0; i < expectedStateCityList.size(); i++) {
            StateCityDTO expectedStateCity = expectedStateCityList.get(i);
            StateCityDTO actualStateCity = result.get(i);
            assertEquals(expectedStateCity.getState(), actualStateCity.getState());
            assertEquals(expectedStateCity.getCities(), actualStateCity.getCities());
        }
    }
}
