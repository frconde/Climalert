package com.climalert.models.repositories;

import com.climalert.models.entities.Clima;

import java.util.List;
import java.util.Optional;

public interface ClimaRepository {
  Clima save(Clima clima);
  List<Clima> findAll();
  void delete(Clima clima);
  Optional<Clima> findUltimo();
}
