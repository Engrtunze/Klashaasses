package com.countriesdata.assessment.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class ReadFile {
    @Value("${exchange.rate.file}")
    private String exchangeRateFile;
    private final Map<String, Double> exchangeRates = new HashMap<>();

    @PostConstruct
    private void init() throws IOException {
        try {
            printCsvFileContents();
            loadExchangeRatesFromFile(exchangeRateFile);
        } catch (IOException e) {
            throw  new IOException(e);
        }
    }


    private void loadExchangeRatesFromFile(String exchangeRateFile) throws IOException {
        try {
            ClassPathResource resource = new ClassPathResource(exchangeRateFile);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String line;
                boolean isFirstLine = true;
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }

                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String sourceCurrency = parts[0].trim();
                        String targetCurrency = parts[1].trim();
                        double rate = Double.parseDouble(parts[2]);
                        exchangeRates.put(sourceCurrency + "-" + targetCurrency, rate);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getExchangeRate(String sourceCurrency, String targetCurrency) {
        String result = sourceCurrency + "-" + targetCurrency;
        return exchangeRates.getOrDefault(result, 0.0);
    }

    public void printCsvFileContents() {

        try {
            ClassPathResource resource = new ClassPathResource(exchangeRateFile);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("line of csv file read   " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
