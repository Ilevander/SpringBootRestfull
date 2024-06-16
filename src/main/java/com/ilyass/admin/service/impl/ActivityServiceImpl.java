package com.ilyass.admin.service.impl;

import com.ilyass.admin.dao.ActivityDao;
import com.ilyass.admin.dao.InstructorDao;
import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.entity.Activity;
import com.ilyass.admin.entity.Instructor;
import com.ilyass.admin.mapper.ActivityMapper;
import com.ilyass.admin.service.ActivityService;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;

public class ActivityServiceImpl implements ActivityService {

    //Inject ActivityDao , ActivityMapper , InstructorDao
    private ActivityDao activityDao;
    private ActivityMapper activityMapper;
    private InstructorDao instructorDao;

    public ActivityServiceImpl(ActivityDao activityDao,ActivityMapper activityMapper,InstructorDao instructorDao) {
        this.activityDao = activityDao;
        this.activityMapper = activityMapper;
        this.instructorDao = instructorDao;
    }


    @Override
    public Activity loadActivityById(Long activityId) {
        return activityDao.findById(activityId).orElseThrow(()->new EntityNotFoundException("Activity With ID "+activityId+" Not Found"));
    }

    @Override
    public ActivityDTO createActivity(ActivityDTO activityDTO) {
        Activity activity = activityMapper.fromActivityDTO(activityDTO);
        Instructor instructor = instructorDao.findById(activityDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor With ID " + activityDTO.getInstructor().getInstructorId() + " Not Found"));
        activity.setInstructor(instructor);
        Activity savedActivity = activityDao.save(activity);
        return activityMapper.fromActivity(savedActivity);
    }

    @Override
    public ActivityDTO updateActivity(ActivityDTO activityDTO) {
        return null;
    }

    @Override
    public Page<ActivityDTO> findActivitiesByActivityName(String keyword, int page, int size) {
        return null;
    }

    @Override
    public void assignStudentToActivity(Long activityId, Long studentId) {

    }

    @Override
    public Page<ActivityDTO> fetchActivitiesForStudent(Long studentId, int page, int size) {
        return null;
    }

    @Override
    public Page<ActivityDTO> fetchNonEnrolledInActivitiesForStudent(Long studentId, int page, int size) {
        return null;
    }

    @Override
    public void deleteActivity(Long activityId) {

    }

    @Override
    public Page<ActivityDTO> fetchActivitiesForInstructor(Long instructorId, int page, int size) {
        return null;
    }
}
