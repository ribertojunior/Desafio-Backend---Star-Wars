package com.example.starwars.controller;

public class PlanetaNotFoundException extends RuntimeException{
  public PlanetaNotFoundException(String nome) {
    super("Planeta não encontrado: " + nome);
  }

  public PlanetaNotFoundException() {
    super("Planeta não encontrado: ");
  }
}
