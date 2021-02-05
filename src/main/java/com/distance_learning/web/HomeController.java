package com.distance_learning.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    @PreAuthorize("! hasAuthority('ADMIN')")
    public String loginPageRedirect(Authentication authResult) {

        Collection<? extends GrantedAuthority> roles =  authResult.getAuthorities();
        if(roles.stream().anyMatch(role -> role.getAuthority().equals("TEACHER"))) {
            return "redirect:/home/teachers";
        }
        if(roles.stream().anyMatch(role -> role.getAuthority().equals("TEACHER"))) {
            return "redirect:/home/students";
        }

        return null;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/students")
    public String studentsPage() {
        return "home";
    }

    @GetMapping("/teachers")
    public String teachersPage() {
        return "home";
    }

}
