package com.example.music.web.controller;

import com.example.music.server.dto.ServerMessageDto;
import com.example.music.server.dto.UserCreateDto;
import com.example.music.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("create")
    public String getUsers(Model model){
        model.addAttribute("user", new UserCreateDto());
        model.addAttribute("users", userService.getUsers());
        return "users/create";
    }

    @PostMapping("create")
    public String createUser(@Valid @ModelAttribute UserCreateDto userCreateDto, BindingResult bindingResult, Model model){
        model.addAttribute("user", new UserCreateDto());
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            model.addAttribute("errors", errors);

        } else if (userService.addUser(userCreateDto)){
            model.addAttribute("success", new ServerMessageDto("The user has been correctly added"));

        }
        return "redirect:/users/create";
    }

    @PostMapping("remove")
    public String removeUser(@RequestParam Long id){
        userService.removeUserById(id);
        return "redirect:/users/create";
    }

    @GetMapping("users")
    public String selectUser(Model model){
        model.addAttribute("users", userService.getUsers());
        return "vote/users";
    }

}
