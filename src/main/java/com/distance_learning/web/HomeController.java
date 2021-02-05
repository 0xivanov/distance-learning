package com.distance_learning.web;

import com.distance_learning.service.services.RoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final RoleService roleService;

    public HomeController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index() {
        roleService.seedRolesInDb();
        return "index";
    }
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/home/admin")
    public String adminPage() {
        return "admin";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public String createCourse() {
        return null;
    }
}
