package com.distance_learning.web.teacher;

import com.distance_learning.service.models.TestServiceModel;
import com.distance_learning.service.services.TestService;
import com.distance_learning.web.models.TestModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/home/teacher")
public class TestController {


    private final TestService testService;
    private final ModelMapper modelMapper;

    public TestController(TestService testService, ModelMapper modelMapper) {
        this.testService = testService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("testModel")
    public TestModel testModel() { return new TestModel(); }

    @GetMapping("/{courseName}/test/create")
    public String createTestPage(@PathVariable String courseName) {
        return "/home/create-test";
    }

    @PostMapping("/{courseName}/test/create")
    public String createTest(@PathVariable String courseName, @ModelAttribute("testModel") TestModel testModel) throws ParseException {
        testModel.setCourse(courseName);
        TestServiceModel testServiceModel = modelMapper.map(testModel,TestServiceModel.class);
        testService.createTest(testServiceModel);
        return "redirect:/home/teacher/" + courseName + '/' +  testModel.getTitle();
    }

    @GetMapping("/{courseName}/{testName}")
    public String editTestPage(@PathVariable String courseName, @PathVariable String testName) {
        return "home/edit-test";
    }
}
