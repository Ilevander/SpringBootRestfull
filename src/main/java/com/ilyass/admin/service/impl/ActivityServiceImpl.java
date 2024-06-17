package com.ilyass.admin.service.impl;

import com.ilyass.admin.dao.ActivityDao;
import com.ilyass.admin.dao.InstructorDao;
import com.ilyass.admin.dao.StudentDao;
import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.entity.Activity;
import com.ilyass.admin.entity.Instructor;
import com.ilyass.admin.entity.Student;
import com.ilyass.admin.mapper.ActivityMapper;
import com.ilyass.admin.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    //Inject ActivityDao , ActivityMapper , InstructorDao
    private ActivityDao activityDao;
    private ActivityMapper activityMapper;
    private InstructorDao instructorDao;
    private StudentDao studentDao;

    public ActivityServiceImpl(ActivityDao activityDao,ActivityMapper activityMapper,InstructorDao instructorDao, StudentDao studentDao) {
        this.activityDao = activityDao;
        this.activityMapper = activityMapper;
        this.instructorDao = instructorDao;
        this.studentDao = studentDao;
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
        Activity loadedActivity = loadActivityById(activityDTO.getActivityId());
        Instructor instructor = instructorDao.findById(activityDTO.getInstructor().getInstructorId()).orElseThrow(() -> new EntityNotFoundException("Instructor With ID " + activityDTO.getInstructor().getInstructorId() + " Not Found"));
        Activity activity = activityMapper.fromActivityDTO(activityDTO);
        activity.setInstructor(instructor);
        activity.setStudents(loadedActivity.getStudents());
        Activity updatedActivity = activityDao.save(activity);
        return activityMapper.fromActivity(updatedActivity);
    }

    @Override
    public Page<ActivityDTO> findActivitiesByActivityName(String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Activity> activityPage = activityDao.findActivitiesByActivityNameContains(keyword,pageRequest);
        return new PageImpl<>(activityPage.getContent().stream().map(activity -> activityMapper.fromActivity(activity)).collect(Collectors.toList()));
    }

    @Override
    public void assignStudentToActivity(Long activityId, Long studentId) {
        Student student = studentDao.findById(studentId).orElseThrow(()->new EntityNotFoundException("Student With ID "+studentId+" Not Found"));
        Activity activity = loadActivityById(activityId);
        activity.assignStudentToActivity(student);
    }

    @Override
    public Page<ActivityDTO> fetchActivitiesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Activity> studentActivitiesPage = activityDao.getActivitiesByStudentId(studentId, pageRequest);
        return new PageImpl<>(studentActivitiesPage.getContent().stream().map(activity -> activityMapper.fromActivity(activity)).collect(Collectors.toList()));
    }

    @Override
    public Page<ActivityDTO> fetchNonEnrolledInActivitiesForStudent(Long studentId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Activity> nonEnrolledInActivitiesPage = activityDao.getNonEnrolledInActivitiesByStudentId(studentId, pageRequest);
        return new PageImpl<>(nonEnrolledInActivitiesPage.getContent().stream().map(activity -> activityMapper.fromActivity(activity)).collect(Collectors.toList()), pageRequest, nonEnrolledInActivitiesPage.getTotalElements());
    }

    @Override
    public void deleteActivity(Long activityId) {
         activityDao.deleteById(activityId);
    }

    @Override
    public Page<ActivityDTO> fetchActivitiesForInstructor(Long instructorId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page <Activity> instructorActivitiesPage = activityDao.getActivitiesByInstructorId(instructorId, pageRequest);
        return new PageImpl<>(instructorActivitiesPage.getContent().stream().map(activity -> activityMapper.fromActivity(activity)).collect(Collectors.toList()), pageRequest, instructorActivitiesPage.getTotalElements());
    }
}
