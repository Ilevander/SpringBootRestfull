package com.ilyass.admin.web;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.dto.InstructorDTO;
import com.ilyass.admin.entity.User;
import com.ilyass.admin.service.ActivityService;
import com.ilyass.admin.service.InstructorService;
import com.ilyass.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructors")
@CrossOrigin("*")
public class InstructorRestController {

    private final InstructorService instructorService;
    private final UserService userService;
    private final ActivityService activityService;

    public InstructorRestController(InstructorService instructorService, UserService userService, ActivityService activityService) {
        this.instructorService = instructorService;
        this.userService = userService;
        this.activityService = activityService;
    }

    @GetMapping
    public Page<InstructorDTO> searchInstructors(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                                 @RequestParam(name = "size", defaultValue = "5") int size) {
        return instructorService.findInstructorByName(keyword, page, size);
    }

    @GetMapping("/all")
    public List<InstructorDTO> findAllInstructors() {
        return instructorService.fetchInstructors();
    }

    @DeleteMapping("/{instructorId}")
    public void deleteInstructor(@PathVariable Long instructorId) {
        instructorService.removeInstructor(instructorId);
    }

    @PostMapping
    public InstructorDTO saveInstructor(@RequestBody InstructorDTO instructorDTO) {
        User user = userService.loadUserByEmail(instructorDTO.getUser().getEmail());
        if (user != null) throw new RuntimeException("Email Already Exist");
        return instructorService.createInstructor(instructorDTO);
    }

    @PutMapping("/{instructorId}")
    public InstructorDTO updateInstructor(@RequestBody InstructorDTO instructorDTO, @PathVariable Long instructorId) {
        instructorDTO.setInstructorId(instructorId);
        return instructorService.updateInstructor(instructorDTO);
    }

    @GetMapping("/{instructorId}/activities")
    public Page<ActivityDTO> activitiesByInstructorId(@PathVariable Long instructorId,
                                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "5") int size) {
        return activityService.fetchActivitiesForInstructor(instructorId, page, size);
    }

    @GetMapping("/find")
    public InstructorDTO loadInstructorByEmail(@RequestParam(name = "email", defaultValue = "") String email) {
        return instructorService.loadInstructorByEmail(email);
    }
}
