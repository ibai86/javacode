package store.mapper;

import store.dto.OrderDto;
import store.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {

    @Mapping(source = "product.id", target = "product.id")
    Order toEntity(OrderDto dto);

    @Mapping(source = "product", target = "product")
    OrderDto toDto(Order order);
}
