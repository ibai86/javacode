package com.task.store.service;

import com.task.store.dto.UserSummaryDto;
import com.task.store.model.User;
import com.task.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public User createUser(UserSummaryDto userDto) {
        String email = userDto.email();
        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("User with such email already exists");
        }
        User newUser = User.builder()
                .username(userDto.username())
                .email(email)
                .build();

        return repository.save(newUser);
    }

    @Transactional
    public User updateUser(UserSummaryDto userDto) {
        User user = repository.findById(userDto.id())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        user.setUsername(userDto.username());
        user.setEmail(userDto.email());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("User not found");
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAll();
    }
}
