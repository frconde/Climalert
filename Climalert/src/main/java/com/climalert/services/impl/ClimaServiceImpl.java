package com.climalert.services.impl;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.climalert.models.dto.external.weatherapi.Current;
import com.climalert.models.dto.external.weatherapi.Location;
import com.climalert.models.dto.external.weatherapi.WeatherResponse;
import com.climalert.models.entities.Clima;
import com.climalert.models.repositories.ClimaRepository;
import org.springframework.stereotype.Service;
import com.climalert.services.ClimaService;
import com.climalert.services.clients.WeatherApiClient;

@Slf4j
@Service
@AllArgsConstructor
public class ClimaServiceImpl implements ClimaService {
  private final WeatherApiClient weatherApiClient;
  private final ClimaRepository climaRepository;
  @Override
  public WeatherResponse obtenerClima() {
    WeatherResponse response = weatherApiClient.solicitarClima();
    Location location = response.location();
    Current current = response.current();
    Clima nuevoClima = new Clima();
      nuevoClima.setCiudad(location.name());
      nuevoClima.setRegion(location.region());
      nuevoClima.setPais(location.country());
      nuevoClima.setTemperaturaCelsius(current.temp_c());
      nuevoClima.setTemperaturaFahrenheit(current.temp_f());
      nuevoClima.setCondicion(current.condition().text());
      nuevoClima.setVelocidadVientoKmh(current.wind_kph());
      nuevoClima.setHumedad(current.humidity());

    climaRepository.save(nuevoClima);
    log.info("Nuevo clima agregado: {}", nuevoClima);
    return response;
  }
}
