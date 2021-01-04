package com.example.starwars.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Document(collection = "planetas")
@Data
@Repository
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Planeta {

  @Transient
  public static final String SEQUENCE_NAME = "users_sequence";

  @Id private Long id;
  @NonNull private String nome;
  @NonNull private String clima;
  @NonNull private String terreno;
  private int numApp;
}
