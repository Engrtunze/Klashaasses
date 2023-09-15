package com.countriesdata.assessment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessageDto {
    private String message;
    private int statusCode;
    private String code;
}
