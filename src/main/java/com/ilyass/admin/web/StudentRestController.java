package com.ilyass.admin.web;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.dto.StudentDTO;
import com.ilyass.admin.entity.User;
import com.ilyass.admin.service.ActivityService;
import com.ilyass.admin.service.StudentService;
import com.ilyass.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentRestController {

    private final StudentService studentService;
    private final UserService userService;
    private ActivityService activityService;

    public StudentRestController(StudentService studentService, UserService userService, ActivityService activityService) {
        this.studentService = studentService;
        this.userService = userService;
        this.activityService = activityService;
    }


    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public Page<StudentDTO> searchStudents(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "5") int size) {
        return studentService.loadStudentsByName(keyword, page, size);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteStudent(@PathVariable Long studentId) {
        studentService.removeStudent(studentId);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public StudentDTO saveStudent(@RequestBody StudentDTO studentDTO) {
        User user = userService.loadUserByEmail(studentDTO.getUser().getEmail());
        if (user != null) throw new RuntimeException("Email Already Exist");
        return studentService.createStudent(studentDTO);
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasAuthority('Student')")
    public StudentDTO updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long studentId) {
        studentDTO.setStudentId(studentId);
        return studentService.updateStudent(studentDTO);
    }

    @GetMapping("/{studentId}/activities")
    @PreAuthorize("hasAuthority('Student')")
    public Page<ActivityDTO> activitiesByStudentId(@PathVariable Long studentId,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "5") int size) {
        return activityService.fetchActivitiesForStudent(studentId, page, size);
    }

    @GetMapping("/{studentId}/other-activities")
    @PreAuthorize("hasAuthority('Student')")
    public Page<ActivityDTO> nonSubscribedActivitiesByStudentId(@PathVariable Long studentId,
                                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "5") int size) {
        return activityService.fetchNonEnrolledInActivitiesForStudent(studentId, page, size);
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('Student')")
    public StudentDTO loadStudentByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        return studentService.loadStudentByEmail(email);
    }
}
