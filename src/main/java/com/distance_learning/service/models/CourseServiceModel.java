package com.distance_learning.service.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CourseServiceModel {
    private String name;
    private Set<UserServiceRegisterModel> users;
}
