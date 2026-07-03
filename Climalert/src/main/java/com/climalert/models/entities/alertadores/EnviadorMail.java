package com.climalert.models.entities.alertadores;

public interface EnviadorMail {
  void enviar(String para, String asunto, String cuerpo);
}
