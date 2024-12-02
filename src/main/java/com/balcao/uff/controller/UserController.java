package com.balcao.uff.controller;

import com.balcao.uff.domain.User;
import com.balcao.uff.domain.dtos.UserDto;
import com.balcao.uff.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findAll() {
        final List<User> users = userService.findAll();
        return ResponseEntity.ok()
                .body(users);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        final UserDto user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto obj) {
        obj = userService.update(obj);
        return ResponseEntity.ok().body(obj);
    }
}
