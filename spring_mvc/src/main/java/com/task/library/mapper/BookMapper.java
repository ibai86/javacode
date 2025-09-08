package com.task.library.mapper;

import com.task.library.dto.BookDto;
import com.task.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class})
public interface BookMapper {

    @Mapping(source = "author.id", target = "author.id")
    Book toEntity(BookDto book);

    @Mapping(source = "author", target = "author")
    BookDto toDto(Book book);

    List<BookDto> toDtoList(List<Book> books);
}
