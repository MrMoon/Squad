package com.squad.user.controller;

import com.squad.user.model.User;
import com.squad.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping(value = "/{S}")
    public ResponseEntity<User> getUserById(@NotNull @PathVariable("userId") String userId) {
        return userService.getUserByUserId(userId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@NotNull @RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        userService.save(user);
        return ResponseEntity.ok(user.toString());
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@NotNull @RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@NotNull @PathVariable("userId") String userId) {
        if(userService.delete(userId)) return ResponseEntity.ok(userId);
        return ResponseEntity.notFound().build();
    }
}
