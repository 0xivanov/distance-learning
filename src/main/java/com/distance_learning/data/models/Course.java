package com.distance_learning.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "courses")
@Getter
@Setter
public class Course extends BaseEntity {

    @Column
    private String name;
    @ManyToMany(mappedBy = "courses")
    private Set<User> users;
}
