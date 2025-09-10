package store.service;

import store.dto.ProductDto;
import store.model.Product;
import store.repository.ProductRepository;
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
