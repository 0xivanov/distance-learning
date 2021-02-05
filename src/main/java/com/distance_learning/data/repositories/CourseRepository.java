package com.distance_learning.data.repositories;

import com.distance_learning.data.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {

}
