package com.aditya.ecommerce.service.impl;

import com.aditya.ecommerce.dtos.checkoutdto.CheckoutRequestDto;
import com.aditya.ecommerce.dtos.checkoutdto.OrderResponseDto;
import com.aditya.ecommerce.entity.*;
import com.aditya.ecommerce.mapper.OrderMapper;
import com.aditya.ecommerce.repository.CartRepository;
import com.aditya.ecommerce.repository.OrderRepository;
import com.aditya.ecommerce.service.IService.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepo;
    private final CartRepository cartRepo;

    public OrderServiceImpl(OrderRepository orderRepo, CartRepository cartRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
    }

    /**
     * This method handles order checkout process.
     *
     * @param checkoutReqDto
     * @return OrderResponseDto
     */
    @Override
    public OrderResponseDto checkout(CheckoutRequestDto checkoutReqDto) {
        Cart cart = cartRepo.findByUserId(checkoutReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart Not Found For given User Id " + checkoutReqDto.getUserId()));
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is Empty !");
        }
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PLACED");
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException("Out of Stock");
            }

            product.setStock(
                    product.getStock() - cartItem.getQuantity()
            );
            OrderItem orderItem = OrderItem.builder()
                    .product(product)
                    .quantity(cartItem.getQuantity())
                    .totalPrice(cartItem.getTotalPrice())
                    .order(order)
                    .build();
            orderItems.add(orderItem);
            total += cartItem.getTotalPrice();
        }
        order.setItems(orderItems);
        order.setTotalAmount(total);
        order.setUser(cart.getUser());
        Order savedOrder = orderRepo.save(order);
        cart.getCartItems().clear();

        return OrderMapper.toResponse(order);
    }

    /**
     * @param userId
     * @return List<OrderResponseDto>
     */
    @Override
    public List<OrderResponseDto> getAllOrders(Long userId) {
        List<Order> resultOrders = orderRepo.findByUserId(userId);
        List<OrderResponseDto> orderResponseDtoList = resultOrders
                .stream()
                .map(OrderMapper::toResponse)
                .toList();
        return orderResponseDtoList;
    }

    /**
     * @param orderId
     * @return OrderResponseDto
     */
    @Override
    public OrderResponseDto getOrder(Long orderId) {
        Order resultOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order Not Found with given Id"));
        return OrderMapper.toResponse(resultOrder);
    }
}
