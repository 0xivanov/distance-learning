package com.distance_learning.web.teacher;

import com.distance_learning.service.services.CourseService;
import com.distance_learning.service.services.UserService;
import com.distance_learning.web.models.CourseModel;
import com.distance_learning.web.models.TestModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;

@Controller
@RequestMapping("/home/teacher")
public class TeacherController {

    private final CourseService courseService;
    private final UserService userService;

    public TeacherController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @ModelAttribute("courseModel")
    public CourseModel courseModel() {
        return new CourseModel();
    }


    @ModelAttribute("testModel")
    public TestModel testModel() { return new TestModel(); }

    @GetMapping
    public String teacherPage(Model model, Principal principal) {
        model.addAttribute("courses", courseService.getCoursesForTeacher(principal.getName()));
        return "/home/teacher";
    }

    @GetMapping("/course/create")
    public String createCoursePage(Model model) {
        model.addAttribute("students" ,userService.getAllStudentsName());
        return "/home/create-course";
    }

    @PostMapping("/course/create")
    public String createCourse(Principal principal, @ModelAttribute("courseModel") CourseModel courseModel) {
        courseService.createCourse(principal.getName()
                , courseModel.getName()
                , courseModel.getUsers());
        return "redirect:/home/teacher";
    }

    @GetMapping("/course/{courseName}")
    public String getCoursePage(@PathVariable String courseName, Model model) {
        model.addAttribute("course", courseName);
        return "/home/course";
    }

    @GetMapping("/course/{courseName}/test/create")
    public String createTestPage(@PathVariable String courseName) {
        return "/home/create-test";
    }

    @PostMapping("/course/{courseName}/test/create")
    public String createTest(@PathVariable String courseName, @ModelAttribute("testModel") TestModel testModel) throws ParseException {
        courseService.createTest(testModel.getName(), testModel.getMessage(), testModel.getDueDate());
        return "/home/create-question";
    }

/*    @GetMapping("/course/{courseName}/test/questions")
    public String createQuestionPage(@PathVariable String courseName) {
        return "/home/create-question";
    }*/
}
