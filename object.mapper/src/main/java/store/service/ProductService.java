package store.service;

import store.dto.ProductDto;
import store.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product getProduct(Long id);

    void deleteProduct(Long id);

    List<Product> getAllProducts();

}
