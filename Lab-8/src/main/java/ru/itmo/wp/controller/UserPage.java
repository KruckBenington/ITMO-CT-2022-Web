package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

import javax.validation.constraints.NotEmpty;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{Id}")
    public String getUser(@NotEmpty @PathVariable String Id, Model model) {
        model.addAttribute("user", userService.findById(userService.getLongId(Id)));
        return "UserPage";
    }
}
