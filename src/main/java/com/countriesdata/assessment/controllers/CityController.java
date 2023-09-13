package com.countriesdata.assessment.controllers;

import com.countriesdata.assessment.dto.CityPopulationData;
import com.countriesdata.assessment.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    @GetMapping(value = "/get-most-populated-city", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CityPopulationData> getMostPopulatedCities(@RequestParam("number-of-city") int cityNumber){
        return cityService.getMostPopulatedCities(cityNumber);
    }
}
