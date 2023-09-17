package com.countriesdata.assessment.service.util;

import com.countriesdata.assessment.exceptions.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelperUtility {
    private final RedisUtility redisUtility;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> List<T> getCachedData(final String cacheKey, Class<T> targetType) {
        String cachedDataJson = redisUtility.getValue(cacheKey);
        if (cachedDataJson != null) {
            return deserializeFromJson(cachedDataJson, targetType);
        }
        return null;
    }

    public <T> void cacheData(List<T> dataToCache, String cacheKey) {
        String dataJson = serializeToJson(dataToCache);
        redisUtility.setValue(cacheKey, dataJson);
    }

    public <T> String serializeToJson(List<T> data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Error serializing data to JSON",e);
        }
    }

    public <T> List<T> deserializeFromJson(String json, Class<T> targetType) {
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, targetType);
            return objectMapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
