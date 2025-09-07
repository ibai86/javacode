package com.task.store.mapper;

import com.task.store.dto.UserDto;
import com.task.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = OrderMapper.class)
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

}
