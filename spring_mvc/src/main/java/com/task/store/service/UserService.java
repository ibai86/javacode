package com.task.store.service;

import com.task.store.dto.UserRequestDto;
import com.task.store.exception.EmailAlreadyExistException;
import com.task.store.model.User;
import com.task.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    @Transactional
    public User createUser(UserRequestDto userDto) {
        String email = userDto.email();
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistException("User with such email already exists");
        }
        User newUser = User.builder()
                .username(userDto.username())
                .email(email)
                .build();

        return repository.save(newUser);
    }

    @Transactional
    public User updateUser(UserRequestDto userDto) {
        User user = getUser(userDto.id());

        user.setUsername(userDto.username());
        user.setEmail(userDto.email());

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

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }
}
