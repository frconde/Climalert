package com.climalert.services.impl;

import lombok.RequiredArgsConstructor;
import com.climalert.models.entities.Clima;
import com.climalert.models.entities.alertadores.EnviadorMail;
import com.climalert.services.AlertMailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertMailServiceImpl implements AlertMailService {
  private static final List<String> CORREOS_DESTINO = List.of(
      "admin@clima.com",
      "emergencias@clima.com",
      "meteorologia@clima.com"

  );

  private static final long MIN_INTERVAL_MS = 30_000;

  private final EnviadorMail enviadorMail;
  private long lastSendTime = 0;

  public void enviarAlerta(Clima clima) {
    String asunto = "Alerta de clima extremo";
    String cuerpo = mapearClima(clima);

    for (String correo : CORREOS_DESTINO) {
      rateLimit();
      enviadorMail.enviar(correo, asunto, cuerpo);
    }
  }

  // Puse esto para que no me estalle por mandar muchos correos en un segundo
  private synchronized void rateLimit() {
    long now = System.currentTimeMillis();
    long wait = MIN_INTERVAL_MS - (now - lastSendTime);
    if (wait > 0) {
      try {
        Thread.sleep(wait);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    lastSendTime = System.currentTimeMillis();
  }
  private String mapearClima(Clima clima) {
    return """
            Se ha detectado una condición climática extrema.

            Ciudad: %s
            Región: %s
            País: %s
            Temperatura: %.1f°C (%.1f°F)
            Humedad: %d%%
            Condición: %s
            Velocidad del viento: %.1f km/h
            Fecha de actualización: %s
            """.formatted(
        clima.getCiudad(),
        clima.getRegion(),
        clima.getPais(),
        clima.getTemperaturaCelsius(),
        clima.getTemperaturaFahrenheit(),
        clima.getHumedad(),
        clima.getCondicion(),
        clima.getVelocidadVientoKmh(),
        clima.getFechaActualizacion()
    );
  }
}
