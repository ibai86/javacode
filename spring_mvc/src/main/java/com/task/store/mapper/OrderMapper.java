package com.task.store.mapper;

import com.task.store.dto.OrderDto;
import com.task.store.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
}
