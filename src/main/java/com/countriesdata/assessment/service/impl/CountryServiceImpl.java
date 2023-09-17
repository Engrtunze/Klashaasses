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
import com.countriesdata.assessment.exceptions.BadRequestException;
import com.countriesdata.assessment.exceptions.NotFoundException;
import com.countriesdata.assessment.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final ApiClient apiClient;
    private final ReadFile readFile;

    @Override
    public CountryGeneralDetailsDTO getCountryDetails(String country) {
        List<CountryCapitalData> countryCapitalData = apiClient.getCountryCapital();
        List<CountryPopulationData> countryPopulationData = apiClient.getCountryPopulation();
        List<CountryLocationData> countryLocationData = apiClient.getCountryLocation();
        List<CountryCurrencyData> countryCurrencyData = apiClient.getCountriesAndCurries();
        var capital = extractCountryCapital(countryCapitalData, country);
        var population = extractCountryPopulation(countryPopulationData, country);
        var location = extractCountryLocation(countryLocationData, country);
        var currency = extractCountryCurrency(countryCurrencyData, country);



        return CountryGeneralDetailsDTO.builder()
                .population(population.getPopulationCounts())
                .capitalCity(capital.getCapital())
                .location(String.format("latitude %s, longitude %s" ,location.getLatitude(), location.getLongitude()))
                .currency(currency.getCurrency())
                .ISO2AND3(String.format("%s ,%s" ,currency.getIso2(), currency.getIso3()))
                .build();
    }

    @Override
    public CountryCurrencyConversionDTO covertCurrency(CountryCurrencyConversionRequest request) {
        List<CountryCurrencyData> countryCurrencyData = apiClient.getCountriesAndCurries();
        var currency = extractCountryCurrency(countryCurrencyData, request.getCountry());

        if (currency.getCurrency().equals(request.getTargetCurrency())) {
            throw new BadRequestException("Source and target currencies are the same");
        }
        double exchangeRate = readFile.getExchangeRate(currency.getCurrency(), request.getTargetCurrency());

        if (exchangeRate == 0.0) {
           throw new BadRequestException("Exchange rate not found");
        }

        double convertedAmount = request.getAmount().doubleValue() * exchangeRate;

        return CountryCurrencyConversionDTO.builder()
                .countryCurrency(currency.getCurrency())
                .convertedCurrencyValue(BigDecimal.valueOf(convertedAmount))
                .build();
    }


    static CountryCapitalData extractCountryCapital(List<CountryCapitalData> countryCapitalList, String countryName) {
        Map<String, CountryCapitalData> countryDataMap = new HashMap<>();
        for (CountryCapitalData countryData : countryCapitalList) {
            countryDataMap.put(countryData.getName(), countryData);
        }

        var countryData =  countryDataMap.get(countryName);

        if (countryData == null) {
            throw new NotFoundException("Country name does not exist");
        }
        return countryData;

    }
    static CountryPopulationData extractCountryPopulation(List<CountryPopulationData> countryPopulationDataList, String countryName) {
        Map<String, CountryPopulationData> countryPopulationDataMap = new HashMap<>();
        for (CountryPopulationData countryPopulationData : countryPopulationDataList) {
            countryPopulationDataMap.put(countryPopulationData.getCountry(), countryPopulationData);
        }

        var populationData =  countryPopulationDataMap.get(countryName);

        if (populationData == null) {
            throw new NotFoundException("Population not found");
        }
        return populationData;
    }

     static CountryLocationData extractCountryLocation(List<CountryLocationData> countryLocationDataList, String countryName) {

         Map<String, CountryLocationData> countryLocationDataMap = new HashMap<>();
         for (CountryLocationData countryLocationData : countryLocationDataList) {
             countryLocationDataMap.put(countryLocationData.getName(), countryLocationData);
         }

         var locationData =  countryLocationDataMap.get(countryName);

         if (locationData == null) {
             throw new NotFoundException("Location not found");
         }
         return locationData;
    }

    static CountryCurrencyData extractCountryCurrency(List<CountryCurrencyData> countryCurrencyDataList, String countryName) {

        Map<String, CountryCurrencyData> countryCurrencyDataMap = new HashMap<>();
        for (CountryCurrencyData countryCurrencyData : countryCurrencyDataList) {
            countryCurrencyDataMap.put(countryCurrencyData.getName(), countryCurrencyData);
        }

        var currencyData =  countryCurrencyDataMap.get(countryName);

        if (currencyData == null) {
            throw new NotFoundException("Currency not found");
        }
        return currencyData;
    }
}
