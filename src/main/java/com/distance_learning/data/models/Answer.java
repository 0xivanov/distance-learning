package com.distance_learning.data.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "answer")
@Getter
@Setter
public class Answer extends BaseEntity {

    @Column
    private String answer;
    @Column
    private boolean isCorrect;
    @ManyToOne
    private Question question;
}
