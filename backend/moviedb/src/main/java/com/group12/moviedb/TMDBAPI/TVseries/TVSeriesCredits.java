// Get the aggregate credits (cast and crew) that have been added to a TV show

package com.group12.moviedb.TMDBAPI.TVseries;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class TVSeriesCredits {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();
    String seriesId = args[0];

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/tv/" + seriesId + "/aggregate_credits?language=en-US")
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}
