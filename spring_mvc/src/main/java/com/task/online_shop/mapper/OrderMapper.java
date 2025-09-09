package com.task.online_shop.mapper;

import com.task.online_shop.dto.OrderDto;
import com.task.online_shop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {

    @Mapping(source = "product.id", target = "product.id")
    Order toEntity(OrderDto dto);

    @Mapping(source = "product", target = "product")
    OrderDto toDto(Order order);
}
