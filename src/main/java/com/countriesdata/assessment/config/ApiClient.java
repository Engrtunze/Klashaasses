package com.countriesdata.assessment.config;

import com.countriesdata.assessment.dto.ApiResponse;
import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.dto.CountryCapitalData;
import com.countriesdata.assessment.dto.CountryCurrencyData;
import com.countriesdata.assessment.dto.CountryLocationData;
import com.countriesdata.assessment.dto.CountryPopulationData;
import com.countriesdata.assessment.dto.StateCityRequest;
import com.countriesdata.assessment.exceptions.BadRequestException;
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
    public List<String> getStates(String country) {
        String endpoint =String.format("%s/%s/states/q?country=%s", devUrl, baseEndpoint, country);
        ResponseEntity<String> responseEntity = sendGetRequest(endpoint);

        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            return extractStateNames(responseEntity.getBody());
        } else {
            throw new BadRequestException("Something went wrong please try again later");
        }
    }

    private ResponseEntity<String> sendGetRequest(String endpoint) {
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
        // Create the request body
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
