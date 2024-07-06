package com.CRUD_Project.mappers;

import com.CRUD_Project.dto.BookDTO;
import com.CRUD_Project.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "genreBookId", source = "genreBook.id")
    BookDTO toDTO(Book book);

    List<BookDTO> toDTOList(List<Book> books);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "genreBook", ignore = true)
    Book toEntity(BookDTO bookDTO);
}
