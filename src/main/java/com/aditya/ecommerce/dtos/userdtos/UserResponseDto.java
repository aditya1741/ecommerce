package com.aditya.ecommerce.dtos.userdtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
}
