package com.distance_learning.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "test")
@Getter
@Setter
public class Test extends BaseEntity {

    @Column
    private String title;
    @Column
    private String message;
    @Column
    @Temporal(TemporalType.TIME)
    private Date dueDate;
    @OneToMany(mappedBy = "test")
    private Set<Question> questions;
    @ManyToOne
    private Course course;
}
