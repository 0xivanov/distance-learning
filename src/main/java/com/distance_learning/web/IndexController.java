package com.distance_learning.web;

import com.distance_learning.service.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final RoleService roleService;

    public IndexController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index() {
        roleService.seedRolesInDb();
        return "index";
    }
}
