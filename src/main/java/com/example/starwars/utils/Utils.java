package com.example.starwars.utils;

import com.example.starwars.entity.Planeta;

public class Utils {
  public static boolean validaPlaneta(Planeta planeta) {
    return planeta != null
        && validaString(planeta.getNome())
        && validaString(planeta.getClima())
        && validaString(planeta.getTerreno());
  }

  public static boolean validaString(String... strings) {
    for (String s : strings) {
      if (!validaString(s)) {
        return false;
      }
    }
    return true;
  }
  private static boolean validaString(String s) {
    return s != null && !s.trim().isEmpty();
  }
}
