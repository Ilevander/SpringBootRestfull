package com.ilyass.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToMany(mappedBy = "activities")
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
}
