package com.distance_learning.web.shared;

import com.distance_learning.service.services.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final RoleService roleService;

    public IndexController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String indexPage(Authentication authResult) {
        if(authResult != null) {
            return "redirect:/logout";
        }
        roleService.seedRolesInDb();
        return "index";
    }
}
