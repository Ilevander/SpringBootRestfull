package com.ilyass.admin.mapper;

import com.ilyass.admin.dto.ActivityDTO;
import com.ilyass.admin.entity.Activity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActivityMapper {

    private InstructorMapper instructorMapper;

    public ActivityMapper(InstructorMapper instructorMapper) {
        this.instructorMapper = instructorMapper;
    }

    public ActivityDTO fromActivity(Activity activity) {
        ActivityDTO activityDTO = new ActivityDTO();
        BeanUtils.copyProperties(activity, activityDTO);
        activityDTO.setInstructor(instructorMapper.fromInstructor(activity.getInstructor()));
        return activityDTO;
    }

    public Activity fromActivityDTO(ActivityDTO activityDTO) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(activityDTO, activity);
        return activity;
    }
}
