package com.example.starwars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Document(collection = "planetas")
@Data
@Repository
@AllArgsConstructor
@NoArgsConstructor
public class Planeta {

  private String nome;
  private String clima;
  private String terreno;
}
