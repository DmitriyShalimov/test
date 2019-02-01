package com.minexsystems.util.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minexsystems.entity.RatesResponseDto;
import com.minexsystems.util.CurrencyLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

@Service
public class FileCurrencyLoader implements CurrencyLoader {

    @Value("${currency.rate.path}")
    private String path;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public RatesResponseDto getRatesDto() {
        RatesResponseDto ratesResponseDto;
        ClassLoader classLoader = this.getClass().getClassLoader();
        try {
            File file = new File(Objects.requireNonNull(classLoader.getResource(path)).getFile());
            String json = new String(Files.readAllBytes(file.toPath()));

            ratesResponseDto = OBJECT_MAPPER.readValue(json, RatesResponseDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while converting json", e);
        }
        return ratesResponseDto;
    }
}
