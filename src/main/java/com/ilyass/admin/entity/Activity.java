package com.ilyass.admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "activities")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;
    private String title;
    private String semester;
    private String subject;
    private String classGroup;
    private String activityDescription;
    private String activityDuration;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToMany
    @JoinTable(
            name = "activity_student",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(activityId, activity.activityId) && Objects.equals(title, activity.title) && Objects.equals(semester, activity.semester) && Objects.equals(subject, activity.subject) && Objects.equals(classGroup, activity.classGroup) && Objects.equals(activityDescription, activity.activityDescription) && Objects.equals(activityDuration, activity.activityDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, title, semester, subject, classGroup, activityDescription, activityDuration);
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
