package com.distance_learning.data.repositories;

import com.distance_learning.data.models.Course;
import com.distance_learning.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> getCoursesByTeacher(User user);
    List<Course> getCoursesByStudents(User user);
}
