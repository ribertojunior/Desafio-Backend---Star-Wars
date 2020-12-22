package com.example.starwars.controller;

import com.example.starwars.entity.Planeta;
import com.example.starwars.repository.PlanetaRepository;
import com.example.starwars.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
public class PlanetaController {

  private final PlanetaRepository repository;
  private final PlanetaAssembler assembler;

  @GetMapping("/planetas")
  CollectionModel<EntityModel<Planeta>> all() {
    List<EntityModel<Planeta>> planetas =
        repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    return CollectionModel.of(
        planetas, linkTo(methodOn(PlanetaController.class).all()).withSelfRel());
  }

  @GetMapping("/planetas/{id}")
  EntityModel<Planeta> one(@PathVariable Long id) {
    Planeta planeta = repository.findById(id).orElseThrow(() -> new PlanetaNotFoundException(id));
    return assembler.toModel(planeta);
  }

  @GetMapping("/planetas/nome/{nome}")
  EntityModel<Planeta> byNome(@PathVariable String nome) {
    if (!Utils.validaString(nome)) throw new PlanetaException("Dados inconsistentes");
    Planeta planeta = repository.findByNome(nome).orElseThrow(PlanetaNotFoundException::new);
    return assembler.toModel(planeta);
  }

  @PostMapping("/planetas")
  ResponseEntity<?> newPlaneta(@RequestBody Planeta planeta) {
    if (!Utils.validaPlaneta(planeta)) throw new PlanetaException("Dados inconsistentes");
    Planeta planetaSaved = repository.save(planeta);
    EntityModel<Planeta> entityModel = assembler.toModel(planetaSaved);
    return ResponseEntity.created(
            linkTo(methodOn(PlanetaController.class).one(planetaSaved.getId()))
                .withSelfRel()
                .toUri())
        .body(entityModel);
  }

  @DeleteMapping("/planetas/{id}")
  ResponseEntity<?> deletePlaneta(@PathVariable Long id) {
    try {
      repository.deleteById(id);
    } catch (Exception e) {
      throw new PlanetaNotFoundException(id);
    }
    return ResponseEntity.noContent().build();
  }
}
