package com.ilyass.admin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id" , nullable = false)
    private Long studentId;

    @Basic
    @Column(name = "first_name",nullable = false,length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name",nullable = false,length = 45)
    private String lastName;

    @Basic
    @Column(name = "student_level",nullable = false,length = 45)
    private String level;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    //Group of students could have Many activities
    @ManyToMany
    @JoinTable(
            name = "enrolled_in",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(level, student.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, level);
    }
}
