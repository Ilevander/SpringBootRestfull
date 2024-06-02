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
    @Column(name = "activity_id" , nullable = false)
    private Long activityId;

    @Basic
    @Column(name = "activity_title",nullable = false,length = 45)
    private String title;

    @Basic
    @Column(name = "activity_semester",nullable = false,length = 45)
    private String semester;

    @Basic
    @Column(name = "activity_subject",nullable = false,length = 45)
    private String subject;

    @Basic
    @Column(name = "activity_classGroup",nullable = false,length = 45)
    private String classGroup;

    @Basic
    @Column(name = "activity_description",nullable = false,length = 64)
    private String activityDescription;

    @Basic
    @Column(name = "activity_duration",nullable = false,length = 45)
    private String activityDuration;

    //Many activities belong to one instructor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",referencedColumnName = "instructor_id")
    private Instructor instructor;

    //Many activities belong to Many students
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enrolled_in",
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
