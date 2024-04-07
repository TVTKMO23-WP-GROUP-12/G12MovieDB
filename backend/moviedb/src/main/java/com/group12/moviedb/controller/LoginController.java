package com.group12.moviedb.controller;

//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  @GetMapping("/login")
  public String home() {
    return
        "<html>\n" +
        "  <head>\n" +
        "    <title>Home</title>\n" +
        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
        "  </head>\n" +
        "  <body>\n" +
        "    <p>\n" +
        "      <a href=\"http://localhost:8080/users\">Users</a>\n" +
        "    </p>\n" +
        "    <p>\n" +
        "      <a href=\"http://localhost:8080/logout\">Log out</a>\n" +
        "    </p>\n" +
        "  </body>\n" +
        "</html>\n";
  }

  /*  KOMMENTOITU POIS, KOSKA USERCONTROLLERISSA ON JO TÄMÄ POLKU. 
      TÄMÄ TARVII EHDON.

  @GetMapping("/users")
  public String getUsers() {
    return "Only users can see this";
  }
  
  */

  @PostMapping("/register")
  public String user() {
    return "";

  }

  }

