package com.ilyass.admin.dao;

import com.ilyass.admin.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityDao extends JpaRepository<Activity, Long> {

    // Update to use the correct field name in the Activity entity
    Page<Activity> findActivitiesByActivityNameContains(String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM activities a WHERE a.activity_id IN (SELECT e.activity_id FROM enrolled_in e WHERE e.student_id = :studentId)", nativeQuery = true)
    Page<Activity> getActivitiesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "SELECT * FROM activities a WHERE a.activity_id NOT IN (SELECT e.activity_id FROM enrolled_in e WHERE e.student_id = :studentId)", nativeQuery = true)
    Page<Activity> getNonEnrolledInActivitiesByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query(value = "SELECT c FROM Activity c WHERE c.instructor.instructorId = :instructorId")
    Page<Activity> getActivitiesByInstructorId(@Param("instructorId") Long instructorId, Pageable pageable);
}
