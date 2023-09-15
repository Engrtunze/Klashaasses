package com.countriesdata.assessment.exceptions;

import com.countriesdata.assessment.dto.ErrorMessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request){
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        errorMessageDto.setMessage(ex.getMessage());
        errorMessageDto.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorMessageDto.setCode("NOT_FOUND");
        return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    @ExceptionHandler(value = {BadRequestException.class, HttpClientErrorException.class})
    protected ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request)  {
        ErrorMessageDto errorMessageDto = new ErrorMessageDto();
        if(ex instanceof HttpClientErrorException){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map<String, Object> message = objectMapper.readValue(((HttpClientErrorException) ex).getResponseBodyAsString(), HashMap.class);
                return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
                return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
            }
        }else{
            errorMessageDto.setMessage(ex.getMessage());
            errorMessageDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
            errorMessageDto.setCode("BAD_REQUEST");
            return handleExceptionInternal(ex, errorMessageDto, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
    }

}
