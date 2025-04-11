package com.practicum.catsgram.controller;

import com.practicum.catsgram.exception.ConditionsNotMetException;
import com.practicum.catsgram.exception.DuplicatedDateException;
import com.practicum.catsgram.exception.NotFondException;
import com.practicum.catsgram.model.User;
import com.practicum.catsgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody User newUser) {
        return userService.update(newUser);
    }
}