package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
