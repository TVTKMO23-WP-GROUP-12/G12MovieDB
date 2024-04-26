package com.group12.moviedb.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ProfilePictureController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/group12/moviedb/static/images";

    @CrossOrigin(origins = "*")
    @PostMapping("/user/{userId}")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file) {
        try {
              // Check file type
              String contentType = file.getContentType();
              if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
                  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported file type. Please upload a JPEG or PNG image.");
              }
            String originalFileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDirectory, originalFileName);
            Files.write(filePath, file.getBytes());
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file: " + e.getMessage());
        }
    }
}