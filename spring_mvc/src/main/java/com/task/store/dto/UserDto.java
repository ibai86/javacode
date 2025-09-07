package com.task.store.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.store.model.Order;
import com.task.store.model.Views;

import java.time.LocalDateTime;
import java.util.List;

public record UserDto(

        @JsonView(Views.UserSummary.class)
        Long id,

        @JsonView(Views.UserSummary.class)
        String username,

        @JsonView(Views.UserSummary.class)
        String email,

        @JsonView(Views.UserDetails.class)
        List<Order> orders,

        @JsonView(Views.UserDetails.class)
        LocalDateTime createdAt,

        @JsonView(Views.UserDetails.class)
        LocalDateTime updatedAt
) {
}
