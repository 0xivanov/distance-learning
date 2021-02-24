package com.distance_learning.web.teacher;

import com.distance_learning.service.models.CourseServiceModel;
import com.distance_learning.service.services.CourseService;
import com.distance_learning.service.services.UserService;
import com.distance_learning.web.models.CourseModel;
import com.distance_learning.web.models.TestModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/home/teacher")
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, UserService userService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("courseModel")
    public CourseModel courseModel() {
        return new CourseModel();
    }

    @GetMapping
    public String teacherPage(Model model, Principal principal) {
        model.addAttribute("courses", courseService.getCoursesForTeacher(principal.getName()));
        return "home/teacher";
    }

    @GetMapping("/course/create")
    public String createCoursePage(Model model) {
        model.addAttribute("students" ,userService.getAllStudentsName());
        return "home/create-course";
    }

    @PostMapping("/course/create")
    public String createCourse(Principal principal, @ModelAttribute("courseModel") CourseModel courseModel, Model model) {
        CourseServiceModel courseServiceModel = modelMapper.map(courseModel, CourseServiceModel.class);
        if(!courseService.createCourse(principal.getName() ,courseServiceModel)) {
            return "redirect:/home/teacher/course/create";
        }
        model.addAttribute(courseService.createCourse(principal.getName() ,courseServiceModel));
        return "redirect:/home/teacher";
    }

    @GetMapping("/{courseName}")
    public String getCoursePage(@PathVariable String courseName, Model model) {
        model.addAttribute("currentCourse", courseService.getCurrentCourse(courseName));
        return "home/course";
    }


}
