package com.aditya.ecommerce.dtos.cartdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AddToCartRequestDto {
    @NotBlank
    private Long userId;
    @NotBlank
    private Long productId;
    @Positive
    private Integer quantity;
}
