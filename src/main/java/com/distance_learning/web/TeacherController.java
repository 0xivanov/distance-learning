package com.distance_learning.web;

import com.distance_learning.service.services.CourseService;
import com.distance_learning.service.services.UserService;
import com.distance_learning.web.models.CourseModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public CourseModel userModel() {
        return new CourseModel();
    }

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
    public String createCourse(Principal principal, @ModelAttribute("courseModel") CourseModel courseModel, BindingResult bindingResult) {
        courseService.createCourse(principal.getName()
                , courseModel.getName()
                , courseModel.getUsers());
        return "redirect:/home/teacher";
    }

    @GetMapping("/course/{courseName}")
    public String getCoursePage(@PathVariable String courseName) {
        return "/home/course";
    }

}
