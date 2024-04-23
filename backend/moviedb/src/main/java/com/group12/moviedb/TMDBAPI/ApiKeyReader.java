package com.group12.moviedb.TMDBAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiKeyReader {

  // MAKE SURE THAT "api-key.yaml" IS IN .GITIGNORE BEFORE YOU PUSH THE CODE!

  // Create a file named "api-key.yaml" in the directory
  // G12MovieDB\backend\moviedb\src\main\resources
  // and write your TMDB API key into it.

  // MAKE SURE THAT "api-key.yaml" IS IN .gitignore BEFORE YOU PUSH THE CODE!

  public static String readApiKey() {
    String apiKeyFileName = "api-key.yaml";

    InputStream inputStream = ApiKeyReader.class.getClassLoader().getResourceAsStream(apiKeyFileName);

    if (inputStream != null) {
      try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
        return br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.err.println("Error: Could not find api-key.yaml in resources folder.");
    }

    return null;
  }
}
