package store.service;

import store.dto.OrderResponseDto;
import store.model.Order;

public interface OrderService {

    Order createOrder(OrderResponseDto orderDto);

    Order getOrder(Long id);
}
