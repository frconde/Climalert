package com.climalert.schedulers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.climalert.services.AlertService;

@AllArgsConstructor
@Component
public class AlertJob {
  private final AlertService alertService;

  @Scheduled(fixedRate = 60000)
  public void revisarCadaMinuto() {
    alertService.analizarUltimoClima();
  }
}
