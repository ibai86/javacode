package com.task.online_shop.service;

import com.task.online_shop.dto.ProductDto;
import com.task.online_shop.model.Product;
import com.task.online_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(ProductDto dto) {
        return null;
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
