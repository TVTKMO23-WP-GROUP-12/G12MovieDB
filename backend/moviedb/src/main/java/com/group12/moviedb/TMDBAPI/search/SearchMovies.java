// Search for movies by their original, translated and alternative titles

package com.group12.moviedb.TMDBAPI.search;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class SearchMovies {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/search/movie?include_adult=false&language=en-US&page=1")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}