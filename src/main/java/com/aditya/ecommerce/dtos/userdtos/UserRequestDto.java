package com.aditya.ecommerce.dtos.userdtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Min(8)
    private String password;
}
