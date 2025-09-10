package store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
