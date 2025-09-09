package com.task.online_shop.service;

import com.task.online_shop.dto.ProductDto;
import com.task.online_shop.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto dto);

    Product updateProduct(ProductDto dto);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    List<Product> getAllProducts();
}
