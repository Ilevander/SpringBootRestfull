package com.ilyass.admin.dao;

import com.ilyass.admin.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityDao extends JpaRepository<Activity,Long> {
    List<Activity> findActivitiesByTitleContains(String keyword);

    @Query(value = "select * from activities as a where a.activity_id in (select e.enrolled_in as e where e.student_id=:studentId)",nativeQuery = true)
    List<Activity> getActivitiesByStudentId(@Param("studentId") Long studentId);
}
