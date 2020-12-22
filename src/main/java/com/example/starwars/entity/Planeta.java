package com.example.starwars.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Document(collation = "planetas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Planeta {

  private @Id
  @GeneratedValue
  Long id;
  @NonNull private String nome;
  @NonNull private String clima;
  @NonNull private String terreno;

}
