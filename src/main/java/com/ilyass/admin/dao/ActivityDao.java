package com.ilyass.admin.dao;

import com.ilyass.admin.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity,Long> {
    List<Activity> findActivitiesByTitleContains(String keyword);

    @Query(value = "SELECT * FROM activities a WHERE a.activity_id IN (SELECT e.activity_id FROM enrolled_in e WHERE e.student_id = :studentId)", nativeQuery = true)
    List<Activity> getActivitiesByStudentId(@Param("studentId") Long studentId);

}
