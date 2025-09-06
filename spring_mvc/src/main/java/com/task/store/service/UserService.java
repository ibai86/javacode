package com.task.store.service;

import com.task.store.model.User;
import com.task.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public User createUser(User user) {
        String email = user.getEmail();
        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with such email already exists");
        }

        return repository.save(user);

    }
}
