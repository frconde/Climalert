package com.climalert.models.dto.external.weatherapi;

public record Current (
  Integer last_updated_epoch,
  String last_updated,
  Double temp_c,
  Double temp_f,
  Condition condition,
  Double wind_kph,
  Integer is_day,
  Integer humidity
){}
