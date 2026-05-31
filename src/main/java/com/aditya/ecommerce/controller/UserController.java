package com.aditya.ecommerce.controller;

import com.aditya.ecommerce.dtos.userdtos.UserRequestDto;
import com.aditya.ecommerce.dtos.userdtos.UserResponseDto;
import com.aditya.ecommerce.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }


    @PostMapping("/register")
    public UserResponseDto register(@Valid @RequestBody UserRequestDto userReqDto){
        return service.register(userReqDto);
    }
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id){
        return service.getUser(id);
    }
}
