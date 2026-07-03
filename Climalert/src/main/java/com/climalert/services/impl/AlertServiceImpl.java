package com.climalert.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.climalert.models.entities.Clima;
import com.climalert.models.repositories.ClimaRepository;
import org.springframework.stereotype.Service;
import com.climalert.services.AlertMailService;
import com.climalert.services.AlertService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
  public class AlertServiceImpl implements AlertService {

    private final ClimaRepository climaRepository;
    private final AlertMailService alertMailService;

    private static final double TEMP_UMBRAL = 35.0;
    private static final int HUMEDAD_UMBRAL = 60;

    @Override
    public void analizarUltimoClima() {
      Optional<Clima> ultClima = climaRepository.findUltimo();

      if (ultClima.isEmpty()) {
        log.info("Aún no hay climas registrados");
        return;
      }

      Clima ultimoClima = ultClima.get();

      if (ultimoClima.estaProcesado()) return;


      boolean esAlerta = ultimoClima.getTemperaturaCelsius() > TEMP_UMBRAL
          && ultimoClima.getHumedad() > HUMEDAD_UMBRAL;

      if (esAlerta) {
        log.info("Se registró una alerta - temperatura: {}°C, humedad: {}%",
            ultimoClima.getTemperaturaCelsius(), ultimoClima.getHumedad());
        alertMailService.enviarAlerta(ultimoClima);
      }



      ultimoClima.setProcesado(true);
      climaRepository.save(ultimoClima);
    }
  }
