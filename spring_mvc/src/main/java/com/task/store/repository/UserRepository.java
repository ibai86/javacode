package com.task.store.repository;

import com.task.store.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    @EntityGraph(attributePaths = "orders")
    Optional<User> findById(Long id);
}
