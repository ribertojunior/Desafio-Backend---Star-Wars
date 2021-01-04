package com.example.starwars.utils;

import com.example.starwars.model.Planeta;

public class Utils {
  public static boolean validaPlaneta(Planeta planeta) {
    return planeta != null
        && validaString(planeta.getNome())
        && validaString(planeta.getClima())
        && validaString(planeta.getTerreno());
  }

  private static boolean validaString(String s) {
    return s != null && !s.trim().isEmpty();
  }
}
