package com.distance_learning.web.shared;

import com.distance_learning.service.models.UserServiceRegisterModel;
import com.distance_learning.service.services.UserService;
import com.distance_learning.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userModel")
    public UserRegisterModel userModel() {
        return new UserRegisterModel();
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("userModel") UserRegisterModel userModel, Authentication authResult) {
        if(authResult != null) {
            return "redirect:/logout";
        }
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid @ModelAttribute("userModel") UserRegisterModel userModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "auth/register";
        } else {
            UserServiceRegisterModel userRegisterModel = modelMapper.map(userModel, UserServiceRegisterModel.class);
            userService.registerUser(userRegisterModel);
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/login")
    public String loginPage(Authentication authResult) {
        if(authResult != null) {
            return "redirect:/logout";
        }
        return "/auth/login";
    }

    @GetMapping("/unauthorized")
    public String unauthorizedPage() { return "/auth/login"; }
}
