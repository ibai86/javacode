package com.task.store.mapper;

import com.task.store.dto.UserDto;
import com.task.store.dto.UserRequestDto;
import com.task.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = OrderMapper.class)
public interface UserMapper {

    UserRequestDto toSummaryDto(User user);

    @Mapping(target = "orders", source = "orders")
    UserDto toDetailsDto(User user);

    User toEntity(UserDto dto);

    List<UserDto> toUsersList(List<User> users);

}
