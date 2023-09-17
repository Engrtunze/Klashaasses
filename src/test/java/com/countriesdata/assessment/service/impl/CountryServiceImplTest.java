package com.countriesdata.assessment.service.impl;

import com.countriesdata.assessment.config.ApiClient;
import com.countriesdata.assessment.config.ReadFile;
import com.countriesdata.assessment.dto.CountryCapitalData;
import com.countriesdata.assessment.dto.CountryCurrencyConversionDTO;
import com.countriesdata.assessment.dto.CountryCurrencyConversionRequest;
import com.countriesdata.assessment.dto.CountryCurrencyData;
import com.countriesdata.assessment.dto.CountryGeneralDetailsDTO;
import com.countriesdata.assessment.dto.CountryLocationData;
import com.countriesdata.assessment.dto.CountryPopulationData;
import com.countriesdata.assessment.dto.PopulationCountData;
import com.countriesdata.assessment.exceptions.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class CountryServiceImplTest {

    @InjectMocks
    private CountryServiceImpl countryService;
    @Mock
    private ApiClient apiClient;

    @Mock
    private ReadFile readFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCountryDetails() {
        List<CountryCapitalData> countryCapitalData = new ArrayList<>();

        CountryCapitalData capitalData = new CountryCapitalData();
        capitalData.setName("Ghana");
        capitalData.setCapital("Capital City");
        capitalData.setIso2("nb");
        capitalData.setIso3("nbd");
        countryCapitalData.add(capitalData);



        List<CountryPopulationData> countryPopulationData = new ArrayList<>();

        CountryPopulationData populationData = new CountryPopulationData();
        populationData.setCountry("Ghana");
        List<PopulationCountData> populationCounts = new ArrayList<>();

        var count1 = new PopulationCountData();
        count1.setYear("2013");
        count1.setValue("11370");
        count1.setSex("Both Sexes");
        count1.setReliabilty("Final figure, complete");

        populationCounts.add(count1);
        populationData.setPopulationCounts(populationCounts);

        countryPopulationData.add(populationData);

        List<CountryLocationData> countryLocationData = new ArrayList<>();
        CountryLocationData locationData = new CountryLocationData();
        locationData.setName("Ghana");
        locationData.setLatitude(12);
        locationData.setLongitude(67);
        countryLocationData.add(locationData);

        List<CountryCurrencyData> countryCurrencyData = new ArrayList<>();
        CountryCurrencyData currencyData = new CountryCurrencyData();
        currencyData.setName("Ghana");
        currencyData.setCurrency("USD");
        currencyData.setIso2("US");
        currencyData.setIso3("USA");
        countryCurrencyData.add(currencyData);

        when(apiClient.getCountryCapital()).thenReturn(countryCapitalData);
        when(apiClient.getCountryPopulation()).thenReturn(countryPopulationData);
        when(apiClient.getCountryLocation()).thenReturn(countryLocationData);
        when(apiClient.getCountriesAndCurries()).thenReturn(countryCurrencyData);

        CountryGeneralDetailsDTO result = countryService.getCountryDetails("Ghana");

        List<PopulationCountData> expectedPopulation = new ArrayList<>();
        expectedPopulation.add(count1);


        String expectedCapitalCity = "Capital City";
        String expectedLocation = "latitude 12, longitude 67";
        String expectedCurrency = "USD";
        String expectedISO2AND3 = "US ,USA";

        assertEquals(expectedPopulation, result.getPopulation());
        assertEquals(expectedCapitalCity, result.getCapitalCity());
        assertEquals(expectedLocation, result.getLocation());
        assertEquals(expectedCurrency, result.getCurrency());
        assertEquals(expectedISO2AND3, result.getISO2AND3());
    }

    @Test
    void testCovertCurrency() {
        List<CountryCurrencyData> countryCurrencyData = new ArrayList<>();

        CountryCurrencyData currencyData = new CountryCurrencyData();
        currencyData.setName("Ghana");
        currencyData.setCurrency("US Dollar");
        countryCurrencyData.add(currencyData);

        when(apiClient.getCountriesAndCurries()).thenReturn(countryCurrencyData);
        when(readFile.getExchangeRate("US Dollar", "TargetCurrency")).thenReturn(1.5);

        CountryCurrencyConversionRequest request = new CountryCurrencyConversionRequest();
        request.setCountry("Ghana");
        request.setTargetCurrency("TargetCurrency");
        request.setAmount(BigDecimal.valueOf(100.0));

        CountryCurrencyConversionDTO result = countryService.covertCurrency(request);

        String expectedCurrency = "US Dollar";
        BigDecimal expectedConvertedValue = BigDecimal.valueOf(150.0);

        assertEquals(expectedCurrency, result.getCountryCurrency());
        assertEquals(expectedConvertedValue, result.getConvertedCurrencyValue());
    }

    @Test
    void testCovertCurrencySameCurrencies() {
        CountryCurrencyConversionRequest request = new CountryCurrencyConversionRequest();
        request.setCountry("Ghana");
        request.setTargetCurrency("US Dollar");
        request.setAmount(BigDecimal.valueOf(100.0));

        List<CountryCurrencyData> countryCurrencyData = new ArrayList<>();
        CountryCurrencyData currencyData = new CountryCurrencyData();
        currencyData.setName("Ghana");
        currencyData.setCurrency("US Dollar");
        countryCurrencyData.add(currencyData);
        when(apiClient.getCountriesAndCurries()).thenReturn(countryCurrencyData);

        assertThrows(BadRequestException.class, () -> countryService.covertCurrency(request));
    }

    @Test
    void testCovertCurrencyExchangeRateNotFound() {
        CountryCurrencyConversionRequest request = new CountryCurrencyConversionRequest();
        request.setCountry("Ghana");
        request.setTargetCurrency("TargetCurrency");
        request.setAmount(BigDecimal.valueOf(100.0));

        List<CountryCurrencyData> countryCurrencyData = new ArrayList<>();
        CountryCurrencyData currencyData = new CountryCurrencyData();
        currencyData.setName("Ghana");
        currencyData.setCurrency("US Dollar");
        countryCurrencyData.add(currencyData);
        when(apiClient.getCountriesAndCurries()).thenReturn(countryCurrencyData);

        when(readFile.getExchangeRate("US Dollar", "TargetCurrency")).thenReturn(0.0);

        assertThrows(BadRequestException.class, () -> countryService.covertCurrency(request));
    }
}
