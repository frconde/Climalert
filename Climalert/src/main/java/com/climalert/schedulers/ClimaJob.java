package com.climalert.schedulers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.climalert.services.ClimaService;

@AllArgsConstructor
@Component
public class ClimaJob {
  private final ClimaService climaService;
  @Scheduled(cron = "0 */5 * * * *")
  public void obtenerClimaCada5m() {
    climaService.obtenerClima();
  }
}
