package com.store.mapper;

import com.store.dto.ProductDto;
import com.store.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductDto dto);

    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> productsDto);
}
