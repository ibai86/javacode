package com.task.store.mapper;

import com.task.store.dto.UserDetailsDto;
import com.task.store.dto.UserSummaryDto;
import com.task.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserSummaryDto toSummaryDto(User user);

    @Mapping(target = "ordersDtos", source = "orders")
    UserDetailsDto toDetailsDto(User user);

    User toEntity(UserSummaryDto dto);

    List<UserSummaryDto> toUsersList(List<User> users);

}
