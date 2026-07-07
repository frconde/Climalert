package com.climalert.models.entities.alertadores;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EnviadorMailImpl implements EnviadorMail {

  private final JavaMailSender javaMailSender;
  private final String direccionRemitente;

  public EnviadorMailImpl(
      JavaMailSender javaMailSender,
      @Value("${mail.from}") String direccionRemitente
  ) {
    this.javaMailSender = javaMailSender;
    this.direccionRemitente = direccionRemitente;
  }

  @Override
  public void enviar(String para, String asunto, String cuerpo) {
    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setFrom(direccionRemitente);
    mensaje.setTo(para);
    mensaje.setSubject(asunto);
    mensaje.setText(cuerpo);
    javaMailSender.send(mensaje);
    System.out.println("Se envió un correo a: " + para);
  }
}