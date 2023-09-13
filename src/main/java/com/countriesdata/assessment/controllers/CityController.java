package com.countriesdata.assessment.controllers;

import com.countriesdata.assessment.dto.CityPopulationDTO;
import com.countriesdata.assessment.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    @GetMapping(value = "/get-most-populated-city", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityPopulationDTO> getMostPopulatedCities(@RequestParam("number-of-city") int cityNumber){
        return cityService.getMostPopulatedCities(cityNumber);
    }
}
