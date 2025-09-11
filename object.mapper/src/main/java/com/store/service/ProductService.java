package com.store.service;

import com.store.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    List<Product> getAllProducts();

    List<Product> saveAllProducts(List<Product> products);

}
