package com.task.store.service;

import com.task.store.dto.UserSummaryDto;
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
}
