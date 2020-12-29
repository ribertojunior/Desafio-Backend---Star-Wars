package com.example.starwars.controller;

import com.example.starwars.model.Planeta;
import com.example.starwars.repository.PlanetaRepository;
import com.example.starwars.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PlanetaController {

  @Autowired private PlanetaRepository repository;
  @Autowired private PlanetaAssembler assembler;

  @GetMapping("/planetas")
  CollectionModel<EntityModel<Planeta>> all() {
    List<EntityModel<Planeta>> planetas =
        repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(
        planetas, linkTo(methodOn(PlanetaController.class).all()).withSelfRel());
  }

  @GetMapping("/planetas/{nome}")
  EntityModel<Planeta> one(@PathVariable String nome) {
    Planeta planeta = repository.findByNome(nome).orElseThrow(() -> new PlanetaNotFoundException(nome));
    return assembler.toModel(planeta);
  }


  @PostMapping("/planetas")
  ResponseEntity<?> newPlaneta(@RequestBody Planeta planeta) {
    if (!Utils.validaPlaneta(planeta)) throw new PlanetaException("Dados inconsistentes");
    Planeta planetaSaved = repository.save(planeta);
    EntityModel<Planeta> entityModel = assembler.toModel(planetaSaved);
    return ResponseEntity.created(
            linkTo(methodOn(PlanetaController.class).one(planetaSaved.getNome()))
                .withSelfRel()
                .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/planetas/{nome}")
  ResponseEntity<?> deletePlaneta(@PathVariable String nome) {
    try {
      repository.deleteByNome(nome);
    } catch (Exception e) {
      throw new PlanetaNotFoundException(nome);
    }
    return ResponseEntity.noContent().build();
  }
}
