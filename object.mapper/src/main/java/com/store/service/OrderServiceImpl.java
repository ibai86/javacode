package com.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.store.dto.OrderResponseDto;
import com.store.exception.InsufficientStockException;
import com.store.model.Customer;
import com.store.model.Order;
import com.store.model.Product;
import com.store.repository.CustomerRepository;
import com.store.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductServiceImpl productService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public Order createOrder(OrderResponseDto dto) {
        Long customerId = dto.customer().id();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        List<Product> checkedProducts = new ArrayList<>();
        dto.productsIds()
                .forEach(id -> {
                    Product product = productService.getProduct(id);
                    int availableQuantity = product.getQuantityInStock();
                    if (availableQuantity == 0) {
                        throw new InsufficientStockException("Product " + product.getName() + " is not present in stock");
                    }
                    product.setQuantityInStock(availableQuantity - 1);
                    checkedProducts.add(product);
                });

        List<Product> productsInOrder = productService.saveAllProducts(checkedProducts);

        Order newOrder = Order.builder()
                .customer(customer)
                .products(productsInOrder)
                .shippingAddress(dto.shippingAddress())
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
}
