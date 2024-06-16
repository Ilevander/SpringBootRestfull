package com.ilyass.admin.service.impl;

import com.ilyass.admin.dao.InstructorDao;
import com.ilyass.admin.dto.InstructorDTO;
import com.ilyass.admin.entity.Instructor;
import com.ilyass.admin.entity.User;
import com.ilyass.admin.mapper.InstructorMapper;
import com.ilyass.admin.service.InstructorService;
import com.ilyass.admin.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorServiceImpl implements InstructorService {

    private InstructorDao instructorDao;
    private InstructorMapper instructorMapper;
    private UserService userService;

    public InstructorServiceImpl(InstructorDao instructorDao, InstructorMapper instructorMapper, UserService userService) {
        this.instructorDao = instructorDao;
        this.instructorMapper = instructorMapper;
        this.userService = userService;
    }

    @Override
    public Instructor loadInstructorById(long instructorId) {
        return instructorDao.findById(instructorId).orElseThrow(()-> new EntityNotFoundException("Instructor with id " + instructorId + " not found"));
    }

    @Override
    public Page<InstructorDTO> findInstructorByName(String name, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Instructor> instructorsPage = instructorDao.findInstructorsByName(name, pageRequest);
        return new PageImpl<>(instructorsPage.getContent().stream().map(instructor -> instructorMapper.fromInstructor(instructor)).collect(Collectors.toList()),pageRequest, instructorsPage.getTotalElements());

    }

    @Override
    public InstructorDTO loadInstructorByEmail(String email) {
        return instructorMapper.fromInstructor(instructorDao.findInstructorByEmail(email));
    }

    @Override
    public InstructorDTO createInstructor(InstructorDTO instructorDTO) {
        User user = userService.createUser(instructorDTO.getUser().getEmail(),instructorDTO.getUser().getPassword());
        userService.assignRoleToUser(user.getEmail(),"Instructor");
        Instructor instructor = instructorMapper.fromInstructorDTO(instructorDTO);
        instructor.setUser(user);
        Instructor savedInstructor = instructorDao.save(instructor);
        return instructorMapper.fromInstructor(savedInstructor);
    }

    @Override
    public InstructorDTO updateInstructor(InstructorDTO instructorDTO) {
        return null;
    }

    @Override
    public List<InstructorDTO> fetchInstructors() {
        return List.of();
    }

    @Override
    public void removeInstructor(Long instructorId) {

    }
}
