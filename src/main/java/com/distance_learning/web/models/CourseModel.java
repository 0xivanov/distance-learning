package com.distance_learning.web.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseModel {
    private String name;
    private Set<String> users;
    private Set<TestModel> tests;
}
