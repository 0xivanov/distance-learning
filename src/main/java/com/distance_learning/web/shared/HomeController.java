package com.distance_learning.web.shared;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping()
    @PreAuthorize("! hasAuthority('ADMIN')")
    public String loginPageRedirect(Authentication authResult) {

        Collection<? extends GrantedAuthority> roles =  authResult.getAuthorities();
        if(roles.stream().anyMatch(role -> role.getAuthority().equals("TEACHER"))) {
            return "redirect:/home/teacher";
        }
        else if(roles.stream().anyMatch(role -> role.getAuthority().equals("STUDENT"))) {
            return "redirect:/home/student";
        } else {
            return null;
        }
    }

    //TODO
/*    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public String adminPage() {
        return "/home/admin";
    }*/

}
