package com.group12.moviedb.controller;

import com.group12.moviedb.TMDBAPI.ApiKeyReader;
import com.group12.moviedb.TMDBAPI.search.SearchResultList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {

  @CrossOrigin(origins = "*")
  @GetMapping("/search")
  public SearchResultList searchMovies(@RequestParam String query, @RequestParam int page) {
    String apiKey = ApiKeyReader.readApiKey();
    RestTemplate restTemplate = new RestTemplate();
    String url = "https://api.themoviedb.org/3/search/multi?query=" + query + "&api_key=" + apiKey + "&page=" + page;
    return restTemplate.getForObject(url, SearchResultList.class);
  }
}
