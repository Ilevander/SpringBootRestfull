package com.ilyass.admin.runner;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.dto.InstructorDTO;
import com.ilyass.admin.dto.UserDTO;
import com.ilyass.admin.service.ActivityService;
import com.ilyass.admin.service.InstructorService;
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

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ActivityService activityService;

    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
        createInstructors();
        createActivity();
    }


    private void createRoles() {
        Arrays.asList("Admin","Instructor","Student").forEach(role-> roleService.createRole(role));
    }

    private void createAdmin() {
        userService.createUser("admin@gmail.com", "1234");
        userService.assignRoleToUser("admin@gmail.com", "Admin");
    }

    private void createInstructors() {
        for (int i = 0; i < 10; i++) {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setFirstName("instructor" + i + "FN");
            instructorDTO.setLastName("instructor" + i + "LN");
            instructorDTO.setSummary("master" + i);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail("instructor" + i + "@gmail.com");
            userDTO.setPassword("1234");
            instructorDTO.setUser(userDTO);
            instructorService.createInstructor(instructorDTO);
        }
    }

    private void createActivity() {
        for (int i = 0; i < 20; i++) {
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setActivityDescription("Java" + i);
            activityDTO.setActivityDuration(i + "Hours");
            activityDTO.setActivityName("Java Course" + i);
            activityDTO.setClassGroup("Group" + i);
            activityDTO.setSemester("Semester" + i);
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setInstructorId(1L);
            activityDTO.setInstructor(instructorDTO);
            activityService.createActivity(activityDTO);
        }
    }
}
