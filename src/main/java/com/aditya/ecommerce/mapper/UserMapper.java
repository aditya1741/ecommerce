package com.aditya.ecommerce.mapper;

import com.aditya.ecommerce.dtos.userdtos.UserResponseDto;
import com.aditya.ecommerce.entity.User;

public class UserMapper {
    public static UserResponseDto toResponseDto(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
