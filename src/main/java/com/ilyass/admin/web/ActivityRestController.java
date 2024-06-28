package com.ilyass.admin.web;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
@CrossOrigin("*")
public class ActivityRestController {

    private final ActivityService activityService;

    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public Page<ActivityDTO> searchActivities(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "5") int size) {
        return activityService.findActivitiesByActivityName(keyword, page, size);
    }

    @DeleteMapping("/{activityId}")
    @PreAuthorize("hasAuthority('Admin')")
    public void deleteActivity(@PathVariable Long activityId) {
        activityService.removeActivity(activityId);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public ActivityDTO saveActivity(@RequestBody ActivityDTO activityDTO) {
        return activityService.createActivity(activityDTO);
    }

    @PutMapping("/{activityId}")
    @PreAuthorize("hasAnyAuthority('Admin','Instructor')")
    public ActivityDTO updateActivity(@RequestBody ActivityDTO activityDTO, @PathVariable Long activityId) {
        activityDTO.setActivityId(activityId);
        return activityService.updateActivity(activityDTO);
    }

    @PostMapping("/{activityId}/enroll/students/{studentId}")
    @PreAuthorize("hasAuthority('Student')")
    public void enrollStudentInActivity(@PathVariable Long activityId, @PathVariable Long studentId) {
        activityService.assignStudentToActivity(activityId, studentId);
    }
}
