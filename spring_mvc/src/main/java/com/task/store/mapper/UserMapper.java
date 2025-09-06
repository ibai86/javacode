package com.task.store.mapper;

import com.task.store.dto.UserSummaryDto;
import com.task.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "", qualifiedByName = "mapOrders")
    UserSummaryDto toSummaryDto(User user);
}
