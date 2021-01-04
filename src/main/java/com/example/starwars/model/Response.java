package com.example.starwars.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Response {
  private int count;
  private String next;
  private List<Results> results;

}
