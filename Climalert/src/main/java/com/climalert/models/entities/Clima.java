package com.climalert.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Clima {
  private Long id;
  private String ciudad;
  private String region;
  private String pais;
  private Double temperaturaCelsius;
  private Double temperaturaFahrenheit;
  private String condicion;
  private Double velocidadVientoKmh;
  private Integer humedad;
  private LocalDateTime fechaActualizacion;
  private boolean procesado;

  public Clima() {
    this.fechaActualizacion = LocalDateTime.now();
    this.procesado = false;
  }
  public boolean estaProcesado() {
    return procesado;
  }
}
