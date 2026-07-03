package com.climalert.models.repositories.inMemory;

import com.climalert.models.entities.Clima;
import com.climalert.models.repositories.ClimaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClima implements ClimaRepository {
  final private List<Clima> climas = new ArrayList<>();
  private Long nextId = 0L;

  @Override
  public List<Clima> findAll() {
    return new ArrayList<>(climas);
  }
  @Override
  public Clima save(Clima clima) {
    delete(clima);
    if(clima.getId() == null)
      clima.setId(nextId++);
    climas.add(clima);
    return clima;
  }

  @Override
  public void delete(Clima clima) {
    if(clima.getId() == null)
      return;
    climas.removeIf(c->c.getId().equals(clima.getId()));

  }

  @Override
  public Optional<Clima> findUltimo() {
    if (climas.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(climas.getLast());
  }
}
