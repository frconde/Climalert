package com.climalert.models.dto.external.weatherapi;

public record WeatherResponse (
    Location location,
    Current current
){}
