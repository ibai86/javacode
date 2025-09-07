package com.task.store.controller;

import com.task.store.dto.UserSummaryDto;
import com.task.store.mapper.UserMapper;
import com.task.store.model.User;
import com.task.store.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;


    @PostMapping("/users")
    public ResponseEntity<UserSummaryDto> createUser(@Valid @RequestBody UserSummaryDto dto) {
        User newUser = userService.createUser(dto);
        return new ResponseEntity<>(mapper.toSummaryDto(newUser), HttpStatus.CREATED);
    }

}
