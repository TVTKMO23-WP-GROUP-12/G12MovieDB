package com.group12.moviedb.controller;

import com.group12.moviedb.TMDBAPI.search.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SearchController {

  @Autowired
  private SearchService searchService;

  @CrossOrigin(origins = "*")
  @GetMapping("/public/search")
  public String searchMovies(@RequestParam String query, @RequestParam int page) {
    return searchService.getSearch(query, page);
  }
}
