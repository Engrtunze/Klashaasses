package com.countriesdata.assessment.service.impl;

import com.countriesdata.assessment.config.ApiClient;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;
    @Mock
    private ApiClient apiClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
//    @Test
//    void testGetCountryDetails() {
//        var capitalData1 = new CountryCapitalData();
//        capital.setCapital("Capital1");
//        capital.setName("abc");
//        capital.setIso3("ndd");
//        capital.setIso2("ds");
//
//        var capitalData2 = new CountryCapitalData();
//        capital.setCapital("Capital2");
//        capital.setName("abcdw");
//        capital.setIso3("yue");
//        capital.setIso2("su");
//
//        List<CountryCapitalData> capitalDataList = Arrays.asList(capitalData1, capitalData2);
//
//        var population = new PopulationCountData();
//        capital.setCapital("Capital1");
//        capital.setName("abc");
//        capital.setIso3("ndd");
//        capital.setIso2("ds");
//
//
//
//        List<CountryPopulationData> populationDataList = Arrays.asList(
//                new CountryPopulationData("Country1", Arrays.asList(new PopulationCountDTO(2020, 1000000))),
//                new CountryPopulationData("Country2", Arrays.asList(new PopulationCountDTO(2020, 2000000)))
//        );
//
//        List<CountryLocationData> locationDataList = Arrays.asList(
//                new CountryLocationData("Country1", 35.0, 45.0),
//                new CountryLocationData("Country2", 40.0, 50.0)
//                // Add more data as needed
//        );
//
//        List<CountryCurrencyData> currencyDataList = Arrays.asList(
//                new CountryCurrencyData("Country1", "Currency1", "ISO2-1", "ISO3-1"),
//                new CountryCurrencyData("Country2", "Currency2", "ISO2-2", "ISO3-2")
//                // Add more data as needed
//        );
//
//        // Mock the apiClient methods
//        when(apiClient.getCountryCapital()).thenReturn(capitalDataList);
//        when(apiClient.getCountryPopulation()).thenReturn(populationDataList);
//        when(apiClient.getCountryLocation()).thenReturn(locationDataList);
//        when(apiClient.getCountriesAndCurries()).thenReturn(currencyDataList);
//
//        // Create an instance of YourService (the service class where getCountryDetails is defined)
//        YourService yourService = new YourService(apiClient);
//
//        // Call the method you want to test
//        CountryGeneralDetailsDTO result = yourService.getCountryDetails("Country1");
//
//        // Assert the result based on your expectations
//        assertEquals("Capital1", result.getCapitalCity());
//        assertEquals(1000000, result.getPopulation().get(0).getValue());
//        assertEquals("latitude 35.0, longitude 45.0", result.getLocation());
//        assertEquals("Currency1", result.getCurrency());
//        assertEquals("ISO2-1 ,ISO3-1", result.getISO2AND3());
//    }
}
