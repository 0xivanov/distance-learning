package com.distance_learning.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "courses")
@Getter
@Setter
public class Course extends BaseEntity {

    @Column
    private String name;
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "course_user",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            )
    )
    private Set<User> students = new HashSet<>();

    @OneToOne
    private User teacher;
    @OneToMany(mappedBy = "course")
    private Set<Test> tests;
}
