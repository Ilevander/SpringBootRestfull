package com.ilyass.admin.web;

import com.ilyass.admin.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('Admin')")
    public boolean checkIfEmailExists(@RequestParam(name = "email",defaultValue = "") String email) {
        return userService.loadUserByEmail(email) != null;
    }
}
