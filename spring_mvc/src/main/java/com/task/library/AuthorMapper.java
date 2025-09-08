package com.task.library;

import com.task.library.dto.AuthorDto;
import com.task.library.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto authorDto);
}
