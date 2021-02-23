package com.distance_learning.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "question")
@Getter
@Setter
public class Question extends BaseEntity{

    @Column
    private String name;
    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;
    @ManyToOne
    private Test test;
}
