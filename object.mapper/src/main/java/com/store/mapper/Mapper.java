package com.store.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dto.ProductDto;
import com.store.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mapper {

    private final ObjectMapper mapper;

    public Product toProduct(ProductDto dto) {
        return mapper.convertValue(dto, Product.class);
    }

    public ProductDto toProductDto(Product product) {
        return mapper.convertValue(product, ProductDto.class);
    }


}
