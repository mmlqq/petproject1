package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public void update(@RequestBody UserDto userDto, @PathVariable Integer id) {
        userService.updateById(userDto, id);
    }


}
