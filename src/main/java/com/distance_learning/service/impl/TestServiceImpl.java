package com.distance_learning.service.impl;

import com.distance_learning.data.models.Test;
import com.distance_learning.data.repositories.CourseRepository;
import com.distance_learning.data.repositories.TestRepository;
import com.distance_learning.service.models.TestServiceModel;
import com.distance_learning.service.services.TestService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class TestServiceImpl implements TestService {

    private final CourseRepository courseRepository;
    private final TestRepository testRepository;

    public TestServiceImpl(CourseRepository courseRepository, TestRepository testRepository) {
        this.courseRepository = courseRepository;
        this.testRepository = testRepository;
    }

    @Override
    public void createTest(TestServiceModel testServiceModel) throws ParseException {
        Test test = new Test();
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
        Date dueDate = formatter.parse(testServiceModel.getDueDate());
        test.setDueDate(dueDate);
        test.setTitle(testServiceModel.getTitle());
        test.setMessage(testServiceModel.getMessage());
        test.setCourse(courseRepository.getCourseByName(testServiceModel.getCourse()));
        testRepository.saveAndFlush(test);
    }
}
