package com.group12.moviedb.TMDBAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ApiKeyReader {
  public static String readApiKey() {

    // MAKE SURE THAT "api-key.yaml" IS IN .GITIGNORE BEFORE YOU PUSH THE CODE!

    // Create a file named "api-key.yaml" in the directory \G12MovieDB\documents and write your TMDB API key into it. 
    
    // MAKE SURE THAT "api-key.yaml" IS IN .gitignore BEFORE YOU PUSH THE CODE!

    String apiKeyFilePath = "../../../../../../documents/api-key.yaml";

    try (BufferedReader br = new BufferedReader(new FileReader(apiKeyFilePath))) {
      return br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}