package com.task.online_shop.dto;

import com.task.online_shop.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        Long id,
        List<ProductDto> products,
        String shippingAddress,
        BigDecimal totalPrice,
        Order.OrderStatus orderStatus,
        LocalDate orderDate
) {
}
