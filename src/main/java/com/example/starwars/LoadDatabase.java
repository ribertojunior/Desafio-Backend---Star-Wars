package com.example.starwars;

import com.example.starwars.entity.Planeta;
import com.example.starwars.repository.PlanetaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

  @Bean
  CommandLineRunner initDatabase(PlanetaRepository repository) {
    return args -> {
      Planeta planeta = repository.save(new Planeta("terra", "árido", "coisa"));
      log.info("Preloading: " + planeta);
    };

  }
}
