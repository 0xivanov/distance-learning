package com.distance_learning.web;

import com.distance_learning.service.models.UserServiceRegisterModel;
import com.distance_learning.service.services.UserService;
import com.distance_learning.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
    public String register(@ModelAttribute("userModel") UserRegisterModel userModel) {
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userModel") UserRegisterModel userModel, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "auth/register";
        } else {
            UserServiceRegisterModel userRegisterModel = modelMapper.map(userModel, UserServiceRegisterModel.class);
            userService.registerUser(userRegisterModel);
            return "redirect:/auth/login";
        }
    }

    @GetMapping("/login")
    public String login() { return "/auth/login"; }

    @GetMapping("/unauthorized")
    public String unauthorized() { return "index"; }
}
