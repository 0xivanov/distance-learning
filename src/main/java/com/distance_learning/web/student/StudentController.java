package com.distance_learning.web.student;

import com.distance_learning.service.services.CourseService;
import com.distance_learning.service.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home/student")
public class StudentController {

    private final CourseService courseService;

    public StudentController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String studentPage(Model model, Principal principal) {
        model.addAttribute("courses", courseService.getCoursesForStudent(principal.getName()));
        return "/home/student";
    }
}
