package com.ilyass.admin.runner;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.dto.InstructorDTO;
import com.ilyass.admin.dto.StudentDTO;
import com.ilyass.admin.dto.UserDTO;
import com.ilyass.admin.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final InstructorService instructorService;
    private final ActivityService activityService;
    private final StudentService studentService;

    @Autowired
    public MyRunner(RoleService roleService, UserService userService, InstructorService instructorService,
                    ActivityService activityService, StudentService studentService) {
        this.roleService = roleService;
        this.userService = userService;
        this.instructorService = instructorService;
        this.activityService = activityService;
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoles();
        createAdmin();
        createInstructors();
        createActivities();
        StudentDTO student = createStudent();
        assignCourseToStudent(student);
        createStudents();
    }


    private void createRoles() {
        Arrays.asList("Admin", "Instructor", "Student").forEach(roleService::createRole);
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

    private void createActivities() {
        for (int i = 0; i < 20; i++) {
            ActivityDTO activityDTO = new ActivityDTO();
            activityDTO.setActivityDescription("Java" + i);
            activityDTO.setActivityDuration(i + "Hours");
            activityDTO.setActivityName("Java Course" + i);
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setInstructorId(1L);
            activityDTO.setInstructor(instructorDTO);
            activityService.createActivity(activityDTO);
        }
    }

    private StudentDTO createStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName("studentFN");
        studentDTO.setLastName("studentLN");
        studentDTO.setLevel("intermediate");
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("student@gmail.com");
        userDTO.setPassword("1234");
        studentDTO.setUser(userDTO);
        return studentService.createStudent(studentDTO);
    }

    private void assignCourseToStudent(StudentDTO student) {
        activityService.assignStudentToActivity(1L, student.getStudentId());
    }

    private void createStudents() {
        for (int i = 1; i < 10; i++) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName("studentFN" + i);
            studentDTO.setLastName("studentLN" + i);
            studentDTO.setLevel("intermediate" + i);
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail("student" + i + "@gmail.com");
            userDTO.setPassword("1234");
            studentDTO.setUser(userDTO);
            studentService.createStudent(studentDTO);

        }
    }
}
