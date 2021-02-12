package com.distance_learning.service.services;

import com.distance_learning.service.models.CourseServiceModel;

import java.util.List;
import java.util.Set;

public interface CourseService {
    void createCourse(String teacherName, String courseName, Set<String> studentsName);
    List<CourseServiceModel> getCoursesForStudent(String username);
    List<CourseServiceModel> getCoursesForTeacher(String username);
}
