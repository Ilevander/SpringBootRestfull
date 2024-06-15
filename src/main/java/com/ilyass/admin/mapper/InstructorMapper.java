package com.ilyass.admin.mapper;

import com.ilyass.admin.dto.InstructorDTO;
import com.ilyass.admin.entity.Instructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/*
Mapping between Entities and DTOs
 */
@Service
public class InstructorMapper {

    public InstructorDTO fromInstructor(Instructor instructor) {
         InstructorDTO instructorDTO = new InstructorDTO();
         BeanUtils.copyProperties(instructor, instructorDTO);
         return instructorDTO;
     }

     public Instructor fromInstructorDTO(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        BeanUtils.copyProperties(instructorDTO, instructor);
        return instructor;
     }
}
