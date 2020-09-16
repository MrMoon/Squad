package com.squad.user.controller;

import com.squad.user.model.User;
import com.squad.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<String> getUserById(@NotNull @PathVariable("userId") String userId) {
        //userService.getUserByUserId(userId).map(value -> ResponseEntity.ok(value.toString())).orElseGet(() -> ResponseEntity.notFound().build());
        this.userService.getUserByUserId(userId);
        return ResponseEntity.ok(userId);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> createUser(@NotNull @RequestBody User user) {
        return ResponseEntity.ok(this.userService.save(user).toString());
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@NotNull @RequestBody User user) {
        return ResponseEntity.ok(this.userService.update(user).toString());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@NotNull @PathVariable("userId") String userId) {
        if(userService.delete(userId)) return ResponseEntity.ok(userId);
        return ResponseEntity.notFound().build();
    }
}
