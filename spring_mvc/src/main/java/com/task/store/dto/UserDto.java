package com.task.store.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.task.store.model.Views;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record UserDto(

        @JsonView(Views.UserSummary.class)
        Long id,

        @JsonView(Views.UserSummary.class)
        String username,

        @JsonView(Views.UserSummary.class)
        String email,

        @JsonView(Views.UserDetails.class)
        List<OrderDto> orders,

        @JsonView(Views.UserDetails.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'#'HH:mm:ss",
                locale = "ru-RU")
        LocalDateTime createdAt,

        @JsonView(Views.UserDetails.class)
        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'#'HH:mm:ss",
                locale = "ru-RU")
        LocalDateTime updatedAt
) {
}
