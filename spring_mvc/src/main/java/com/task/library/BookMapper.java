package com.task.library;

import com.task.library.dto.BookDto;
import com.task.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "id", ignore = true)
    Book toEntity(BookDto book);

    BookDto toDto(Book book);
}
