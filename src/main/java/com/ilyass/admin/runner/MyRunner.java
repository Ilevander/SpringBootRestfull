package com.ilyass.admin.runner;

import com.ilyass.admin.service.RoleService;
import com.ilyass.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;
    
    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
    }

    private void createRoles() {
        Arrays.asList("Admin","Instructor","Student").forEach(role-> roleService.createRole(role));
    }

    private void createAdmin() {
        userService.createUser("admin@gmail.com", "1234");
        userService.assignRoleToUser("admin@gmail.com", "Admin");
    }
}
