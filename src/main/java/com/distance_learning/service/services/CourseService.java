package com.distance_learning.service.services;

import com.distance_learning.service.models.CourseServiceModel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CourseService {
    void createCourse(String teacherName, String courseName, Set<String> studentsName);
    List<CourseServiceModel> getCoursesForStudent(String username);
    List<CourseServiceModel> getCoursesForTeacher(String username);
    void createTest(String title, String message, String dueDate) throws ParseException;
}
