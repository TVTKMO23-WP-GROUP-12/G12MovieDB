// Get the release dates and certifications for a movie

package com.group12.moviedb.TMDBAPI.movies;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class MoviesReleaseDates {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();
    String movieId = args[0];

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/movie/" + movieId + "/release_dates")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}
