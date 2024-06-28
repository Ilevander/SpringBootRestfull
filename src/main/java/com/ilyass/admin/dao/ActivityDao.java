package com.ilyass.admin.dao;

import com.ilyass.admin.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityDao extends JpaRepository<Activity, Long> {

    Page<Activity> findActivitiesByActivityNameContains(String keyword, Pageable pageable);

    @Query(value = "select * from activities where activity_id in (select e.activity_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    Page<Activity> getActivitiesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "select * from activities where activity_id not in (select e.activity_id from enrolled_in as e where e.student_id=:studentId)", nativeQuery = true)
    Page<Activity> getNonEnrolledInActivitiesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "select c from Activity c where c.instructor.instructorId=:instructorId")
    Page<Activity> getActivitiesByInstructorId(@Param("instructorId") Long instructorId, Pageable pageable);
}
