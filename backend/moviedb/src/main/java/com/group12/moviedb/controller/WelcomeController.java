package com.group12.moviedb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Arrays;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<List<String>> welcome() {
        return ResponseEntity.ok(Arrays.asList("first", "second"));
    }

}
