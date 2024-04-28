package com.group12.moviedb.TMDBAPI.movieLists;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import org.springframework.stereotype.Service;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;

@Service
public class TopRatedService {
    private final OkHttpClient client = new OkHttpClient();
    
    public String getTopRatedMovies() throws IOException {
        String apiKey = ApiKeyReader.readApiKey();

        Request request = new Request.Builder()
            .url("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1")
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