package com.climalert.services;
import com.climalert.models.dto.external.weatherapi.WeatherResponse;

public interface ClimaService {
  WeatherResponse obtenerClima();
}
