package com.group12.moviedb.TMDBAPI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class PerformHttpRequest {

  // Function to perform the HTTP request

  public static void handleHttpRequest(Request request) {
    OkHttpClient client = new OkHttpClient();
    try {
      Response response = client.newCall(request).execute();
      if (response.isSuccessful()) {
        String responseData = response.body().string();
        System.out.println("API Response: " + responseData);
      } else {
        System.out.println("Error: " + response.code() + " " + response.message());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}