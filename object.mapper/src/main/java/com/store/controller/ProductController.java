package com.store.controller;

import com.store.dto.ProductDto;
import com.store.mapper.Mapper;
import com.store.model.Product;
import com.store.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@Valid @RequestBody ProductDto dto) {
        Product newProduct = productService.createProduct(mapper.toProduct(dto));
        return mapper.toProductDto(newProduct);
    }

    @PutMapping
    public ProductDto updateProduct(@Valid @RequestBody ProductDto dto) {
        Product updatedProduct = productService.updateProduct(mapper.toProduct(dto));
        return mapper.toProductDto(updatedProduct);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@Positive @PathVariable Long id) {
        return mapper.toProductDto(productService.getProduct(id));
    }

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAllProducts().stream().map(mapper::toProductDto).toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@Positive @PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
