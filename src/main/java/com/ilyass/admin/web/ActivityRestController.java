package com.ilyass.admin.web;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/activities")
@CrossOrigin("*")
public class ActivityRestController {

    private ActivityService activityService;

    public ActivityRestController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public Page<ActivityDTO> searchActivities(@RequestParam(name = "keyword", defaultValue = "") String keyword,
                                           @RequestParam(name = "page", defaultValue = "0") int page,
                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        return activityService.findActivitiesByActivityName(keyword, page, size);
    }

    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable Long activityId) {
        activityService.deleteActivity(activityId);
    }

    @PostMapping
    public ActivityDTO saveActivity(@RequestBody ActivityDTO activityDTO) {
        return activityService.createActivity(activityDTO);
    }

    @PutMapping("/{activityId}")
    public ActivityDTO updateActivity(@RequestBody ActivityDTO activityDTO, @PathVariable Long activityId) {
        activityDTO.setActivityId(activityId);
        return activityService.updateActivity(activityDTO);
    }

    @PostMapping("/{activityId}/enroll/students/{studentId}")
    public void enrollStudentInActivity(@PathVariable Long activityId, @PathVariable Long studentId) {
        activityService.assignStudentToActivity(activityId, studentId);
    }
}
