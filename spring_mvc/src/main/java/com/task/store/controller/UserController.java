package com.task.store.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.store.dto.UserDto;
import com.task.store.dto.UserRequestDto;
import com.task.store.mapper.UserMapper;
import com.task.store.model.User;
import com.task.store.model.Views;
import com.task.store.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;


    @PostMapping("/users")
    public ResponseEntity<UserRequestDto> createUser(@Valid @RequestBody UserRequestDto dto) {
        User newUser = userService.createUser(dto);
        return new ResponseEntity<>(mapper.toDto(newUser), HttpStatus.CREATED);
    }

    @PatchMapping("/users")
    public ResponseEntity<UserRequestDto> updateUser(@Valid @RequestBody UserRequestDto dto) {
        User updatedUser = userService.updateUser(dto);
        return new ResponseEntity<>(mapper.toDto(updatedUser), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@Positive @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    @JsonView({Views.UserDetails.class})
//    @GetMapping("/id")
//    public ResponseEntity
    @JsonView(Views.UserDetails.class)
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(mapper.toDtoList(users), HttpStatus.OK);
    }

}
