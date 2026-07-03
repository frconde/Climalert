package com.climalert.services;

import com.climalert.models.entities.Clima;

public interface AlertMailService {
  void enviarAlerta(Clima clima);
}
