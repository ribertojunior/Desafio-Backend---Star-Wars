package com.example.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results {
  private String name;
  private String climate;
  private String terrain;
  private List<String> films;
  private Integer filmsSize;

  public void calculaFilmsSize() {
    this.filmsSize = films != null ? films.size() : 0;
  }
}
