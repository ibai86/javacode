package com.store.service;

import com.store.dto.OrderRequestDto;
import com.store.exception.InsufficientStockException;
import com.store.model.Customer;
import com.store.model.Order;
import com.store.model.Product;
import com.store.repository.CustomerRepository;
import com.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Order createOrder(OrderRequestDto dto) {
        Customer customer = customerRepository.findById(dto.customerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        List<Product> checkedProducts = new ArrayList<>();
        dto.productIds()
                .forEach(id -> {
                    Product product = productService.getProduct(id);
                    int availableQuantity = product.getQuantityInStock();
                    if (availableQuantity == 0) {
                        throw new InsufficientStockException("Product " + product.getName() + " is out of stock");
                    }
                    product.setQuantityInStock(availableQuantity - 1);
                    checkedProducts.add(product);
                });

        List<Product> productsInOrder = productService.saveAllProducts(checkedProducts);

        Order newOrder = Order.builder()
                .customer(customer)
                .products(productsInOrder)
                .shippingAddress(dto.shippingAddress())
                .totalPrice(getTotalPrice(productsInOrder))
                .orderStatus(Order.OrderStatus.PROCESSED)
                .build();

        return orderRepository.save(newOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    private BigDecimal getTotalPrice(List<Product> products) {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
