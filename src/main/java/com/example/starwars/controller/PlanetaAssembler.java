package com.example.starwars.controller;

import com.example.starwars.model.Planeta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PlanetaAssembler implements RepresentationModelAssembler<Planeta, EntityModel<Planeta>> {

  @Override
  public EntityModel<Planeta> toModel(Planeta entity) {
    return EntityModel.of(
        entity,
        linkTo(methodOn(PlanetaController.class).one(entity.getNome())).withSelfRel(),
        linkTo(methodOn(PlanetaController.class).all()).withRel("planetas"));
  }
}
