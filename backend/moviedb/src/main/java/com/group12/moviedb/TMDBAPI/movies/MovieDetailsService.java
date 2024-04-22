package com.group12.moviedb.TMDBAPI.movies;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class MovieDetailsService {
    private final OkHttpClient client = new OkHttpClient();

    public String getMovieDetails(String movieId) {
        String apiKey = ApiKeyReader.readApiKey();

        Request request = new Request.Builder()
            .url("https://api.themoviedb.org/3/movie/" + movieId + "?language=en-US")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer " + apiKey)
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}