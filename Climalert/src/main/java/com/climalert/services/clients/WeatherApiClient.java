package com.climalert.services.clients;
import com.climalert.models.dto.external.weatherapi.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class WeatherApiClient {

  private final RestTemplate restTemplate;

  @Value("${weatherapi.base-url}")
  private String baseUrl;

  @Value("${weatherapi.key}")
  private String apiKey;

  private static final String CIUDAD = "Buenos Aires";

  public WeatherResponse solicitarClima() {
    String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/current.json")
        .queryParam("key", apiKey)
        .queryParam("q", CIUDAD)
        .toUriString();

    return restTemplate.getForObject(url, WeatherResponse.class);
  }
}
