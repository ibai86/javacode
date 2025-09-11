package com.store.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.dto.CustomerDto;
import com.store.dto.OrderResponseDto;
import com.store.dto.ProductDto;
import com.store.model.Customer;
import com.store.model.Order;
import com.store.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public CustomerDto toCustomerDto(Customer customer) {
        return mapper.convertValue(customer, CustomerDto.class);
    }

    public OrderResponseDto toOrderResponseDto(Order order) {
        OrderResponseDto dto = mapper.convertValue(order, OrderResponseDto.class);

        CustomerDto customerDto = toCustomerDto(order.getCustomer());

        List<Long> productsIds = order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());

        return dto.setResponseValues(customerDto, productsIds);
    }


}
