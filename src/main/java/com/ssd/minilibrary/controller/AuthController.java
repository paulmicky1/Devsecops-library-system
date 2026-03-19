package com.ssd.minilibrary.controller;

import com.ssd.minilibrary.dto.UserRegistrationDto;
import com.ssd.minilibrary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private static final String REGISTER_VIEW = "register";

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        return REGISTER_VIEW;
    }

    @PostMapping("/register")
    public String registerUser(
        @Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return REGISTER_VIEW;
        }

        try {
            userService.registerUser(userRegistrationDto);
        } catch (IllegalArgumentException ex) {
            bindingResult.rejectValue("username", "username.exists", ex.getMessage());
            return REGISTER_VIEW;
        }

        return "redirect:/login?success";
    }
}
