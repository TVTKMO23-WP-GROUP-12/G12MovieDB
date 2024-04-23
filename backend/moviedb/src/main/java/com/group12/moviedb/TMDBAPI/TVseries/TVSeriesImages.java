// Get the images that belong to a TV series

package com.group12.moviedb.TMDBAPI.TVseries;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class TVSeriesImages {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();
    String seriesId = args[0];

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/tv/" + seriesId + "/images")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}
