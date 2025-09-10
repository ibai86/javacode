package store.service;

import store.dto.ProductDto;
import store.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto dto);

    Product updateProduct(ProductDto dto);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    List<Product> getAllProducts();
}
