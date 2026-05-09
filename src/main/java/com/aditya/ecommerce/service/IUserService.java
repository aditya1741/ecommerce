package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.userdtos.UserRequestDto;
import com.aditya.ecommerce.dtos.userdtos.UserResponseDto;


public interface IUserService {
    UserResponseDto register(UserRequestDto userReq);

    UserResponseDto getUser(Long Id);
}
