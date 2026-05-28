package com.aditya.ecommerce.service;

import com.aditya.ecommerce.dtos.cartdto.AddToCartRequestDto;
import com.aditya.ecommerce.dtos.cartdto.CartResponseDto;
import com.aditya.ecommerce.entity.Cart;
import com.aditya.ecommerce.entity.CartItem;
import com.aditya.ecommerce.entity.Product;
import com.aditya.ecommerce.entity.User;
import com.aditya.ecommerce.exception.ProductNotFoundException;
import com.aditya.ecommerce.exception.UserNotFoundException;
import com.aditya.ecommerce.mapper.CartMapper;
import com.aditya.ecommerce.repository.CartRepository;
import com.aditya.ecommerce.repository.ProductRepository;
import com.aditya.ecommerce.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public CartServiceImpl(CartRepository cartRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    @Override
    public CartResponseDto addToCart(AddToCartRequestDto addToCartReqDto) {
        System.out.println("user Id" + addToCartReqDto.getUserId());
        User user = userRepo.findById(addToCartReqDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        Product product = productRepo.findById(addToCartReqDto.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
        if (addToCartReqDto.getQuantity() > product.getStock()) {
            throw new RuntimeException("Selected Product Quantity is Greater than stock.");
        }
        Cart cart = cartRepo.findByUserId(user.getId()).orElseGet(() -> createCart(user));
        Optional<CartItem> existingCartItem = cart.getCartItems()
                .stream().filter(item -> item
                        .getProduct()
                        .getId()
                        .equals(addToCartReqDto.getProductId()))
                .findFirst();
        if (existingCartItem.isPresent()) {
            CartItem item = existingCartItem.get();
            item.setQuantity(item.getQuantity() + addToCartReqDto.getQuantity());
            item.setTotalPrice(item.getQuantity() * item.getProduct().getPrice());
        } else {

            CartItem cartItem = CartItem.builder()
                    .product(product)
                    .quantity(addToCartReqDto.getQuantity())
                    .totalPrice(addToCartReqDto.getQuantity() * product.getPrice())
                    .cart(cart)
                    .build();
            cart.getCartItems().add(cartItem);
        }
        cartRepo.save(cart);
        return CartMapper.toCartResponse(cart);

    }

    private @NotNull Cart createCart(User user) {
        Cart cart = Cart.builder()
                .user(user)
                .createdAt(LocalDateTime.now())
                .cartItems(new ArrayList<CartItem>())
                .build();
        return cartRepo.save(cart);
    }

    @Override
    public CartResponseDto getCart(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new RuntimeException("User Not Found");
        }

        Cart resultCart = cartRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart not Found For Given User id " + userId));

        return CartMapper.toCartResponse(resultCart);
    }

    @Override
    public CartResponseDto removeItem(Long userId, Long cartItemId) {
        Cart cart = cartRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("Cart Not Found For given User Id"));
        CartItem resultCartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst().orElseThrow(() -> new RuntimeException("Cart Item Not Found"));
        cart.getCartItems().remove(resultCartItem);

        cartRepo.save(cart);
        return CartMapper.toCartResponse(cart);
    }
}
