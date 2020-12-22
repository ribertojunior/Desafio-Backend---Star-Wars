package com.example.starwars.repository;

import com.example.starwars.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
  Optional<Planeta> findByNome(String nome);
}
