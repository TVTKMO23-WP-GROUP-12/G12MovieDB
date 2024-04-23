// Get a movie or TV credit details by ID

package com.group12.moviedb.TMDBAPI.credits;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class CreditsDetails {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();
    String creditId = args[0]; // Assuming credit ID is passed as a command line argument

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/credit/" + creditId)
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}