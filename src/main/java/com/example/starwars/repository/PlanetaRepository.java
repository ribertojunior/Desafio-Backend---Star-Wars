package com.example.starwars.repository;

import com.example.starwars.entity.Planeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, Long> {
  Optional<Planeta> findByNome(String nome);
}
