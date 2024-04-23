package com.group12.moviedb.TMDBAPI;

public class TestApiKeyReader {
    public static void main(String[] args) {
        String apiKey = ApiKeyReader.readApiKey();
        System.out.println("API Key: " + apiKey);
    }
}
