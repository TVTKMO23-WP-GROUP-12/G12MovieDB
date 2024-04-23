package com.group12.moviedb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/admin/myAdminPage")
    public String getAdminPage() {
        return "multipleHttpElems/myAdminPage";
    }

    @GetMapping("/user/general/myUserPage")
    public String getUserPage() {
        return "multipleHttpElems/myUserPage";
    }

    @GetMapping("/user/private/myPrivateUserPage")
    public String getPrivateUserPage() {
        return "multipleHttpElems/myPrivateUserPage"; 
    }

    @GetMapping("/group/myGroupPage")
    public String getGuestPage() {
        return "multipleHttpElems/myGuestPage";
    }

    @GetMapping("/multipleHttpLinks")
    public String getMultipleHttpLinksPage() {
        return "multipleHttpElems/multipleHttpLinks";
    }
}