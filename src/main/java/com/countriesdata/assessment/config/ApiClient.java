package com.countriesdata.assessment.config;

import com.countriesdata.assessment.dto.ApiResponse;
import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.dto.CountryCapitalData;
import com.countriesdata.assessment.dto.CountryCurrencyData;
import com.countriesdata.assessment.dto.CountryLocationData;
import com.countriesdata.assessment.dto.CountryPopulationData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class ApiClient {
    @Value("${dev-url}")
    private  String devUrl;
    @Value("${base-endpoint}")
    private  String baseEndpoint;

    private final AppConfig appConfig;

    public List<CityPopulationData> getCitiesPopulationData(){
        String endpoint = String.format("%s/%s/population/cities", devUrl,baseEndpoint);
        ResponseEntity<ApiResponse<CityPopulationData>> response =
                appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CityPopulationData>>() {});
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public List<CountryCurrencyData> getCountriesAndCurries(){
        String endpoint = String.format("%s/%s/currency", devUrl,baseEndpoint);
        ResponseEntity<ApiResponse<CountryCurrencyData>> response =
                appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CountryCurrencyData>>() {});
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public List<CountryCapitalData> getCountryCapital(){
        String endpoint = String.format("%s/%s/capital", devUrl,baseEndpoint);
        ResponseEntity<ApiResponse<CountryCapitalData>> response =
                appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CountryCapitalData>>() {});
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public List<CountryLocationData> getCountryLocation(){
        String endpoint = String.format("%s/%s/positions", devUrl,baseEndpoint);
        ResponseEntity<ApiResponse<CountryLocationData>> response =
                appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CountryLocationData>>() {});
        return Objects.requireNonNull(response.getBody()).getData();
    }

    public List<CountryPopulationData> getCountryPopulation(){
        String endpoint = String.format("%s/%s/population", devUrl,baseEndpoint);
        ResponseEntity<ApiResponse<CountryPopulationData>> response =
                appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CountryPopulationData>>() {});
        return Objects.requireNonNull(response.getBody()).getData();
    }




}
