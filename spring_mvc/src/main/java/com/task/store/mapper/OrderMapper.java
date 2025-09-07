package com.task.store.mapper;

import com.task.store.dto.OrderDto;
import com.task.store.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);

    List<OrderDto> toDtoList(List<Order> orders);
}
