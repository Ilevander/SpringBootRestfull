package com.ilyass.admin.dao;

import com.ilyass.admin.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity,Long> {
        Page<Activity> findActivitiesByTitleContains(String keyword , Pageable pageable);

    @Query(value = "SELECT * FROM activities a WHERE a.activity_id IN (SELECT e.activity_id FROM enrolled_in e WHERE e.student_id = :studentId)", nativeQuery = true)
    Page<Activity> getActivitiesByStudentId(@Param("studentId") Long studentId , Pageable pageable);

    @Query(value = "select * from activities as a where a.activity_id not in (select e.activity_id from enrolled_in as e where e.student_id=:studentId)")
    Page<Activity> getNonEnrolledInActivitiesByStudentId(@Param("studentId") Long studentId , Pageable pageable);

}
