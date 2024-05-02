package com.group12.moviedb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group12.moviedb.request.ShareRequest;

@RestController
@RequestMapping("/api")
public class ShareController {

    @PostMapping("/share")
    public ResponseEntity<?> shareContent(@RequestBody ShareRequest shareRequest) {
        // Extract data from the request body
        Integer userId = shareRequest.getUserId();
        String content = shareRequest.getContent();
        String url = shareRequest.getUrl();

        // Validate the request payload
        if (userId == null || content == null || url == null) {
            return ResponseEntity.badRequest().body("Missing required fields");
        }

         //notificationService.sendNotification(recipientUserId, "You have received shared content");

        return ResponseEntity.ok("Content shared successfully");
    }
}