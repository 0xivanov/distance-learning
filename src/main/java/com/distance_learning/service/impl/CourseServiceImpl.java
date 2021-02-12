package com.distance_learning.service.impl;

import com.distance_learning.data.models.Course;
import com.distance_learning.data.models.User;
import com.distance_learning.data.repositories.CourseRepository;
import com.distance_learning.data.repositories.UserRepository;
import com.distance_learning.service.models.CourseServiceModel;
import com.distance_learning.service.services.CourseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createCourse(String teacherName, String courseName , Set<String> studentsName) {
        Course course = new Course();
        course.setName(courseName);
        courseRepository.saveAndFlush(course);
        Set<User> students = studentsName.stream()
                .map(name -> userRepository.findByUsername(name))
                .collect(Collectors.toSet());
        course.getStudents().addAll(students);
        User teacher = userRepository.findByUsername(teacherName);
        course.setTeacher(teacher);
        courseRepository.saveAndFlush(course);
    }

    @Override
    public List<CourseServiceModel> getCoursesForTeacher(String username) {
        User user = userRepository.findByUsername(username);
        List<Course> courses = courseRepository.getCoursesByTeacher(user);
        List<CourseServiceModel> courseServiceModel = modelMapper
                .map(courses, new TypeToken<List<CourseServiceModel>>() {}.getType());
        return courseServiceModel;
    }
    @Override
    public List<CourseServiceModel> getCoursesForStudent(String username) {
        User user = userRepository.findByUsername(username);
        List<Course> courses = courseRepository.getCoursesByStudents(user);
        List<CourseServiceModel> courseServiceModel = modelMapper
                .map(courses, new TypeToken<List<CourseServiceModel>>() {}.getType());
        return courseServiceModel;
    }
}
