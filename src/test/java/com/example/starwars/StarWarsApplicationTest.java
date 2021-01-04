package com.example.starwars;

import com.example.starwars.model.Planeta;
import com.example.starwars.model.Response;
import com.example.starwars.model.Results;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
class StarWarsApplicationTest {
  @Test
  void restApi() {
    RestTemplate restTemplate = new RestTemplate();

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
      ObjectMapper mapper =  new ObjectMapper();
      assert response != null;
      for (Results results : response.getResults()) {
        results.calculaFilmsSize();
        Planeta planeta = modelMapper.map(results, Planeta.class);
        log.info(planeta.toString());
      }
      log.info(String.valueOf(response));
      url = response.getNext() != null ? response.getNext().replace("http", "https") : null;
      log.info(url);
    }while (url != null);
  }

}