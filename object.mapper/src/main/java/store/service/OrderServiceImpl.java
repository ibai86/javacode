package store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.dto.OrderDto;
import store.exception.InsufficientStockException;
import store.model.Customer;
import store.model.Order;
import store.model.Product;
import store.repository.CustomerRepository;
import store.repository.OrderRepository;

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
    public Order createOrder(OrderDto dto) {
        Long customerId = dto.customer().id();
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));

        List<Product> checkedProducts = new ArrayList<>();
        dto.productsDto()
                .forEach(productDto -> {
                    Product product = productService.getProduct(productDto.id());
                    int availableQuantity = product.getQuantityInStock();
                    if (productDto.quantityInOrder() > availableQuantity) {
                        throw new InsufficientStockException(
                                "Quantity of product " + product.getName() + " is not enough is stock");
                    }
                    product.setQuantityInStock(availableQuantity - productDto.quantityInOrder());
                    checkedProducts.add(product);
                });

        List<Product> productsInOrder = productService.saveAllProducts(checkedProducts);

        Order newOrder = Order.builder()
                .customer(customer)
                .products(productsInOrder)
                .shippingAddress(dto.shippingAddress())
                .orderStatus(Order.OrderStatus.PROCESSED)
//                .totalPrice()
                .build();

    }

    @Override
    public Order getOrder(Long id) {
        return null;
    }
}
