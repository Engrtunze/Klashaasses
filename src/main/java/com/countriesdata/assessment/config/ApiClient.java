package com.countriesdata.assessment.config;

import com.countriesdata.assessment.dto.CityPopulationDTO;
import com.countriesdata.assessment.dto.CityPopulationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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

    public List<CityPopulationDTO> getCitiesPopulationData(){
        String endpoint = String.format("%s/%s/population/cities", devUrl,baseEndpoint);
        ResponseEntity<CityPopulationResponse> response = appConfig.restTemplate().exchange(endpoint, HttpMethod.GET, null, CityPopulationResponse.class);
        return Objects.requireNonNull(response.getBody()).getData();
    }



}
