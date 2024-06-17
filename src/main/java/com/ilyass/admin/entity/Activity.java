package com.ilyass.admin.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Basic
    @Column(name = "activity_name", nullable = false, length = 45)
    private String activityName;

    @Basic
    @Column(name = "activity_duration", nullable = false, length = 45)
    private String activityDuration;

    @Basic
    @Column(name = "activity_description", nullable = false, length = 255)
    private String activityDescription;

    @Basic
    @Column(name = "class_group", nullable = false, length = 45)
    private String classGroup;

    @Basic
    @Column(name = "semester", nullable = false, length = 45)
    private String semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "instructor_id", nullable = false)
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enrolled_in", // Ensure this table exists in your database
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(activityId, activity.activityId) &&
                Objects.equals(activityName, activity.activityName) &&
                Objects.equals(activityDuration, activity.activityDuration) &&
                Objects.equals(activityDescription, activity.activityDescription) &&
                Objects.equals(classGroup, activity.classGroup) &&
                Objects.equals(semester, activity.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, activityName, activityDuration, activityDescription, classGroup, semester);
    }

    public void assignStudentToActivity(Student student) {
        this.students.add(student);
        student.getActivities().add(this);
    }

    public void removeStudentFromActivity(Student student) {
        this.students.remove(student);
        student.getActivities().remove(this);
    }
}
