package com.ilyass.admin.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    @Basic
    @Column(name = "activity_name", nullable = false, length = 45)
    private String activityName;
    @Basic
    @Column(name ="activity_duration", nullable = false, length = 45)
    private String activityDuration;
    @Basic
    @Column(name="activity_description", nullable = false, length = 64)
    private String activityDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id",referencedColumnName = "instructor_id",nullable = false)
    private Instructor instructor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "enrolled_in",
            joinColumns = {@JoinColumn(name="activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> students = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return activityId.equals(activity.activityId) && Objects.equals(activityName, activity.activityName) && Objects.equals(activityDuration, activity.activityDuration) && Objects.equals(activityDescription, activity.activityDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityId, activityName, activityDuration, activityDescription);
    }

    public void assignStudentToActivity(Student student) {
        this.students.add(student);
        student.getActivities().add(this);
    }

    public void removeStudentFromActivity(Student student) {
        this.students.remove(student);
        student.getActivities().remove(this);
    }

    public Activity() {
    }

    public Activity(String activityName, String activityDuration, String activityDescription, Instructor instructor) {
        this.activityName = activityName;
        this.activityDuration = activityDuration;
        this.activityDescription = activityDescription;
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", activityDuration='" + activityDuration + '\'' +
                ", activityDescription='" + activityDescription + '\'' +
                '}';
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDuration() {
        return activityDuration;
    }

    public void setActivityDuration(String activityDuration) {
        this.activityDuration = activityDuration;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
