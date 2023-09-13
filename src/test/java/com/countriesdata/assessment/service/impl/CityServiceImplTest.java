package com.countriesdata.assessment.service.impl;

import com.countriesdata.assessment.config.ApiClient;
import com.countriesdata.assessment.dto.CityPopulationDTO;
import com.countriesdata.assessment.dto.PopulationCountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class CityServiceImplTest {
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
        List<CityPopulationDTO> sampleData = new ArrayList<>();

        var count1 = new PopulationCountDTO();
        count1.setYear("2013");
        count1.setValue("11370");
        count1.setSex("Both Sexes");
        count1.setReliabilty("Final figure, complete");

        var count2 = new PopulationCountDTO();
        count1.setYear("2012");
        count1.setValue("11304");
        count1.setSex("Both Sexes");
        count1.setReliabilty("Final figure, complete");


        CityPopulationDTO city1 = new CityPopulationDTO();
        city1.setCity("City1");
        city1.setCountry("Italy");
        city1.setPopulationCounts(Arrays.asList(count1, count2));

        CityPopulationDTO city2 = new CityPopulationDTO();
        city2.setCity("City2");
        city2.setCountry("New Zealand");
        city2.setPopulationCounts(Arrays.asList(count1, count2));

        sampleData.add(city1);
        sampleData.add(city2);

        when(apiClient.getCitiesPopulationData()).thenReturn(sampleData);

        List<CityPopulationDTO> result = cityService.getMostPopulatedCities(2);

        System.out.println("Sample Data: " + sampleData);
        System.out.println("Filtered and Sorted Data: " + result);

        assertEquals(2, result.size());
    }
}
