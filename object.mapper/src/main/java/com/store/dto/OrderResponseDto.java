package com.store.dto;

import com.store.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponseDto(
        Long id,
        CustomerDto customer,
        List<Long> productsIds,
        String shippingAddress,
        BigDecimal totalPrice,
        Order.OrderStatus orderStatus,
        LocalDate orderDate
) {
}
