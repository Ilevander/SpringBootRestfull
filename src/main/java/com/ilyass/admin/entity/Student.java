package com.ilyass.admin.entity;

import lombok.*;

import javax.persistence.*;
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


    //Group of students could have Many activities
    @ManyToMany(mappedBy = "students" , fetch = FetchType.LAZY)
    private Set<Activity> activities = new HashSet<>();


    //Only one Student could be just as a One User
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",nullable = false)
    private User user;


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

    public Student(String firstName, String lastName, String level, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.level = level;
        this.user = user;
    }
}
