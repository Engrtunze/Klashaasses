package com.countriesdata.assessment.controllers;

import com.countriesdata.assessment.dto.CountryGeneralDetailsDTO;
import com.countriesdata.assessment.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping(value = "/get-country-details", produces = MediaType.APPLICATION_JSON_VALUE)
    public CountryGeneralDetailsDTO getCountryDetails(@Validated @RequestParam("country") String country){
        return countryService.getCountryDetails(country);
    }
}
