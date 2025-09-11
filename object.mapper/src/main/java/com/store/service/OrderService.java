package com.store.service;

import com.store.dto.OrderRequestDto;
import com.store.dto.OrderResponseDto;
import com.store.model.Order;

public interface OrderService {

    Order createOrder(OrderRequestDto orderDto);

    Order getOrder(Long id);
}
