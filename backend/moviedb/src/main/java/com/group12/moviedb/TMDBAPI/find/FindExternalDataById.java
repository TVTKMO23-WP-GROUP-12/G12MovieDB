// Find data by external ID's such as IMDb, TheTVDB etc

package com.group12.moviedb.TMDBAPI.find;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.PerformHttpRequest;

import okhttp3.Request;

public class FindExternalDataById {
  public static void main(String[] args) {
    // API-key reader from ApiKeyReader.java
    String apiKey = ApiKeyReader.readApiKey();
    String externalId = args[0]; // Assuming external ID is passed as a command line argument
    String externalSource = args[1]; // Assuming external source is passed as a command line argument

    Request request = new Request.Builder()
        .url("https://api.themoviedb.org/3/find/" + externalId + "?external_source=" + externalSource)
        .get()
        .addHeader("accept", "application/json")
        .addHeader("Authorization", "Bearer " + apiKey)
        .build();

    // Http request handler from PerformHttpRequest.java
    PerformHttpRequest.handleHttpRequest(request);
  }
}