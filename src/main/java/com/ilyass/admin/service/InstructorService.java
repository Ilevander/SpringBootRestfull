package com.ilyass.admin.service;

import com.ilyass.admin.dto.InstructorDTO;
import com.ilyass.admin.entity.Instructor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InstructorService {

    Instructor loadInstructorById(long instructorId);
    Page<InstructorDTO> findInstructorByName(String name,int page,int size);
    InstructorDTO loadInstructorByEmail(String email);
    InstructorDTO createInstructor(InstructorDTO instructorDTO);
    InstructorDTO updateInstructor(InstructorDTO instructorDTO);
    List<InstructorDTO> fetchInstructors();
    void removeInstructor(Long instructorId);
}
