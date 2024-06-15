package com.ilyass.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instructor_id",nullable = false)
    private Long instructorId;

    @Basic
    @Column(name = "first_name",nullable = false,length = 45)
    private String firstName;

    @Basic
    @Column(name = "last_name",nullable = false,length = 45)
    private String lastName;

    @Basic
    @Column(name = "summary",nullable = false,length = 65)
    private String summary;

    //Only one instructor could have a group of activities
    @OneToMany(mappedBy = "instructor",fetch = FetchType.LAZY)
    private Set<Activity> activities = new HashSet<>();

    //Only one Instructor could be an only one User (cascade->REMOVE:to remove also user during removing instructor)
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id",nullable = false)
    private User user;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(instructorId, that.instructorId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(summary, that.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instructorId, firstName, lastName, summary);
    }

    public Instructor(String firstName, String lastName, String summary, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.summary = summary;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", summary='" + summary + '\'' +
                ", user=" + (user != null ? "User{id=" + user.getUserId() + "}" : "null") +
                '}';
    }
}
