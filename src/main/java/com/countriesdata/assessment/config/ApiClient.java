package com.countriesdata.assessment.config;

import com.countriesdata.assessment.dto.ApiResponse;
import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.dto.CountryCapitalData;
import com.countriesdata.assessment.dto.CountryCurrencyData;
import com.countriesdata.assessment.dto.CountryLocationData;
import com.countriesdata.assessment.dto.CountryPopulationData;
import com.countriesdata.assessment.dto.StateCityRequest;
import com.countriesdata.assessment.exceptions.BadRequestException;
import com.countriesdata.assessment.service.util.HelperUtility;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
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
    private final HelperUtility helperUtility;



    public List<CityPopulationData> getCitiesPopulationData(){
        String cacheKey = "citiesPopulationData";
        List<CityPopulationData> cityPopulationCachedData = helperUtility.getCachedData(cacheKey, CityPopulationData.class);
        if(cityPopulationCachedData != null){
            return  cityPopulationCachedData;
        }else {
            String endpoint = String.format("%s/%s/population/cities", devUrl,baseEndpoint);
            ResponseEntity<ApiResponse<CityPopulationData>> response =
                    appConfig.restTemplate().exchange(
                            endpoint,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponse<CityPopulationData>>() {});
            List<CityPopulationData> responseData =  Objects.requireNonNull(response.getBody()).getData();
            helperUtility.cacheData(responseData, cacheKey);
            return responseData;
        }
    }

    public List<CountryCurrencyData> getCountriesAndCurries(){
        String cacheKey = "countryCurrencyData";
        List<CountryCurrencyData> countryCurrencyDataList = helperUtility.getCachedData(cacheKey, CountryCurrencyData.class);
        if(countryCurrencyDataList != null){
            return  countryCurrencyDataList;
        }else {
            String endpoint = String.format("%s/%s/currency", devUrl,baseEndpoint);
            ResponseEntity<ApiResponse<CountryCurrencyData>> response =
                    appConfig.restTemplate().exchange(
                            endpoint,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponse<CountryCurrencyData>>() {});
            List<CountryCurrencyData> responseData = Objects.requireNonNull(response.getBody()).getData();
            helperUtility.cacheData(responseData,cacheKey);
            return responseData;
        }

    }

    public List<CountryCapitalData> getCountryCapital(){
        String cacheKey = "countryCapitalData";
        List<CountryCapitalData> countryCapitalDataList = helperUtility.getCachedData(cacheKey, CountryCapitalData.class);
        if(countryCapitalDataList != null){
            return  countryCapitalDataList;
        }else {
            String endpoint = String.format("%s/%s/capital", devUrl,baseEndpoint);
            ResponseEntity<ApiResponse<CountryCapitalData>> response =
                    appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, new ParameterizedTypeReference<ApiResponse<CountryCapitalData>>() {});
            List<CountryCapitalData> responseData = Objects.requireNonNull(response.getBody()).getData();
            helperUtility.cacheData(responseData, cacheKey);
            return responseData;
        }

    }

    public List<CountryLocationData> getCountryLocation(){
        String cacheKey = "countryLocation";
        List<CountryLocationData> countryLocationDataList = helperUtility.getCachedData(cacheKey, CountryLocationData.class);
        if(countryLocationDataList != null){
            return  countryLocationDataList;
        }else {
            String endpoint = String.format("%s/%s/positions", devUrl,baseEndpoint);
            ResponseEntity<ApiResponse<CountryLocationData>> response =
                    appConfig.restTemplate().exchange(
                            endpoint,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponse<CountryLocationData>>() {});
            List<CountryLocationData> responseData = Objects.requireNonNull(response.getBody()).getData();
            helperUtility.cacheData(responseData, cacheKey);
            return responseData;
        }

    }

    public List<CountryPopulationData> getCountryPopulation(){
        String cacheKey = "countryPopulation";
        List<CountryPopulationData> countryPopulationDataList = helperUtility.getCachedData(cacheKey, CountryPopulationData.class);
        if(countryPopulationDataList != null){
            return  countryPopulationDataList;
        } else {
            String endpoint = String.format("%s/%s/population", devUrl,baseEndpoint);
            ResponseEntity<ApiResponse<CountryPopulationData>> response =
                    appConfig.restTemplate().exchange(
                            endpoint,
                            HttpMethod.GET,
                            null,
                            new ParameterizedTypeReference<ApiResponse<CountryPopulationData>>() {});
            List<CountryPopulationData> responseData = Objects.requireNonNull(response.getBody()).getData();
            helperUtility.cacheData(responseData, cacheKey);
            return responseData;
        }

    }
    public List<String> getStates(String country) {
        List<String> stateList = helperUtility.getCachedData(country, String.class);
        if(stateList != null){
            return  stateList;
        }else {
            String endpoint =String.format("%s/%s/states/q?country=%s", devUrl, baseEndpoint, country);
            ResponseEntity<String> responseEntity = sendGetStateRequest(endpoint);

            if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
                List<String> responseData = extractStateNames(responseEntity.getBody());
                helperUtility.cacheData(responseData, country);
                return responseData;
            } else {
                throw new BadRequestException("Something went wrong please try again later");
            }

        }
    }

    private ResponseEntity<String> sendGetStateRequest(String endpoint) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, requestEntity, String.class);
    }

    private List<String> extractStateNames(String responseBody) {
        List<String> stateNames = new ArrayList<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            JsonNode dataNode = jsonNode.get("data");

            if (dataNode != null) {
                JsonNode statesNode = dataNode.get("states");

                if (statesNode != null && statesNode.isArray()) {
                    for (JsonNode stateNode : statesNode) {
                        JsonNode nameNode = stateNode.get("name");

                        if (nameNode != null) {
                            stateNames.add(nameNode.asText());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stateNames;
    }


    public List<String> getCities(String country, String state){
       String endpoint = String.format("%s/%s/state/cities/q?country=%s&state=%s", devUrl,baseEndpoint,country, state);
        StateCityRequest requestBody = new StateCityRequest();
        requestBody.setCountry(country);
        requestBody.setState(state);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StateCityRequest> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = appConfig.restTemplate().exchange(
                endpoint,
                HttpMethod.GET,
                requestEntity,
                String.class
        );
        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            String responseBody = responseEntity.getBody();
            List<String> cities = new ArrayList<>();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                JsonNode dataNode = jsonNode.get("data");
                if (dataNode != null && dataNode.isArray()) {
                    for (JsonNode cityNode : dataNode) {
                        cities.add(cityNode.asText());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return cities;
        } else {
           throw new BadRequestException("Something went wrong please try again later");
        }
    }
}
