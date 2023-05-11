package com.youssef.Controllers;


import com.youssef.dto.RegistrationDto;
import com.youssef.entities.User;
import com.youssef.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("login")
    public String getLoginForm() {

        return "login";

    }


    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/saveuser")
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        User existingUsername = userService.findUserByUsername(user.getUsername());
        if (existingUsername != null) {
            return "redirect:/register?fail";
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:login";
    }

    @GetMapping("/access")
    public String error()
    {
        return "auth";
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) throws ServletException
    {
        request.logout();
        return "redirect:login";
    }
}
