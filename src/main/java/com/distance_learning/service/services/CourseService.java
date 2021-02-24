package com.distance_learning.service.services;

import com.distance_learning.service.models.CourseServiceModel;

import java.util.List;

public interface CourseService {
    boolean createCourse(String teacherName, CourseServiceModel courseServiceModel);
    List<CourseServiceModel> getCoursesForStudent(String username);
    List<CourseServiceModel> getCoursesForTeacher(String username);
    CourseServiceModel getCurrentCourse(String courseName);
}
