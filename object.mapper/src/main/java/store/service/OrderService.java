package store.service;

import store.dto.OrderDto;
import store.model.Order;

public interface OrderService {

    Order createOrder(OrderDto orderDto);

    Order getOrder(Long id);
}
