package com.distance_learning.service.services;

import com.distance_learning.service.models.TestServiceModel;

import java.text.ParseException;

public interface TestService {
    void createTest(TestServiceModel testServiceModel) throws ParseException;
}
