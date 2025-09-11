package com.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.store.model.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(value = {"customer, productIds"})
public record OrderResponseDto(
        Long id,
        CustomerDto customer,
        List<Long> productIds,
        String shippingAddress,
        BigDecimal totalPrice,
        Order.OrderStatus orderStatus,
        LocalDate orderDate
) {
    public OrderResponseDto setResponseValues(CustomerDto customerDto, List<Long> productIds) {
        return new OrderResponseDto(
                this.id,
                customerDto,
                productIds,
                this.shippingAddress,
                this.totalPrice,
                this.orderStatus,
                this.orderDate
                );
    }
}
