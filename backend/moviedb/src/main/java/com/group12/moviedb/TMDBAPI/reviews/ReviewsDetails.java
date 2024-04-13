// Retrieve the details of a movie or TV show review

package com.group12.moviedb.TMDBAPI.reviews;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class ReviewsDetails {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();
    String reviewId = args[0]; // Assuming review ID is passed as a command line argument

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/review/" + reviewId)
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}