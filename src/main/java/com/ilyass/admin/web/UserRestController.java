package com.ilyass.admin.web;

import com.ilyass.admin.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public boolean checkIfEmailExists(@RequestParam(name = "email",defaultValue = "") String email) {
        return userService.loadUserByEmail(email) != null;
    }
}
