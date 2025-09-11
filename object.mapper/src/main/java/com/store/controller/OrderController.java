package com.store.controller;

import com.store.dto.OrderRequestDto;
import com.store.dto.OrderResponseDto;
import com.store.model.Order;
import com.store.service.OrderService;
import com.store.mapper.Mapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseDto createOrder(@Valid @RequestBody OrderRequestDto dto) {
        Order newOrder = orderService.createOrder(dto);
        return mapper.toOrderResponseDto(newOrder);
    }

    @GetMapping("/{id}")
    public OrderResponseDto getOrder(@Positive @PathVariable Long id) {
        return mapper.toOrderResponseDto(orderService.getOrder(id));
    }
}
