package com.example.starwars.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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
