package com.ilyass.admin.service;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.entity.Activity;
import org.springframework.data.domain.Page;

public interface ActivityService {
    Activity loadActivityById(Long activityId);
    ActivityDTO createActivity(ActivityDTO activityDTO);
    ActivityDTO updateActivity(ActivityDTO activityDTO);
    Page<ActivityDTO> findActivitiesByActivityName(String keyword, int page, int size);
    void assignStudentToActivity(Long activityId, Long studentId);
    Page<ActivityDTO> fetchActivitiesForStudent(Long studentId, int page, int size);
    Page<ActivityDTO> fetchNonEnrolledInActivitiesForStudent(Long studentId, int page, int size);
    void deleteActivity(Long activityId);
    Page<ActivityDTO> fetchActivitiesForInstructor(Long instructorId, int page, int size);

}
