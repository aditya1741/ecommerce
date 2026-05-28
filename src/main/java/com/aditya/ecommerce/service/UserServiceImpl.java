package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.userdtos.UserRequestDto;
import com.aditya.ecommerce.dtos.userdtos.UserResponseDto;
import com.aditya.ecommerce.entity.User;
import com.aditya.ecommerce.exception.UserNotFoundException;
import com.aditya.ecommerce.mapper.UserMapper;
import com.aditya.ecommerce.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    public List<User> getUser() {
        return repo.findAll();
    }



    @Override
    public UserResponseDto register(UserRequestDto userReq) {
        User user = User.builder()
                .name(userReq.getName())
                .email(userReq.getEmail())
                .role("CUSTOMER")
                .password(userReq.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
        User savedUser = repo.save(user);

        return UserMapper.toResponseDto(savedUser);


    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = repo.findById(id).
                orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return UserMapper.toResponseDto(user);
    }
}
