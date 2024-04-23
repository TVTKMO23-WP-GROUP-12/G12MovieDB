// Get a list of movies that are being released soon

package com.group12.moviedb.TMDBAPI.movieLists;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class MoviesUpcoming {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}