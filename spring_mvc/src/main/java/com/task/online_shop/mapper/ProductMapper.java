package com.task.online_shop.mapper;

import com.task.online_shop.dto.ProductDto;
import com.task.online_shop.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto dto);

    ProductDto toDto(Product product);

//    List<ProductDto> toDtoList(List<Product> products);
}
