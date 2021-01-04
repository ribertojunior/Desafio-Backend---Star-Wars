package com.example.starwars;

import com.example.starwars.model.Planeta;
import com.example.starwars.model.Response;
import com.example.starwars.model.Results;
import com.example.starwars.repository.PlanetaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class StarWarsApplication {
  public static void main(String[] args) {
    SpringApplication.run(StarWarsApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }


  @Bean
  public CommandLineRunner run(RestTemplate restTemplate, PlanetaRepository repository) throws Exception {
    return args -> {
      if (repository.count() > 0) repository.deleteAll();
      String url = "https://swapi.dev/api/planets/";
      ResponseEntity<Response> responseEntity;
      Response response;
      ModelMapper modelMapper = new ModelMapper();
      modelMapper.typeMap(Results.class, Planeta.class).addMappings(mapper -> {
        mapper.map(Results::getName, Planeta::setNome);
        mapper.map(Results::getClimate, Planeta::setClima);
        mapper.map(Results::getTerrain, Planeta::setTerreno);
        mapper.map(Results::getFilmsSize, Planeta::setNumApp);
      });
      do {
        responseEntity = restTemplate.getForEntity(url, Response.class);
        response = responseEntity.getBody();
        if (response != null) {
          for (Results results : response.getResults()) {
            results.calculaFilmsSize();
            Planeta planeta = modelMapper.map(results, Planeta.class);
            repository.save(planeta);
          }
          log.info(String.valueOf(response));
          url =
              response.getNext() != null
                  ? response.getNext().replace("http", "https")
                  : null;
          log.info(url);
        }
      } while (url != null);
    };
  }
}
