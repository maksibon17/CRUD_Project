package com.CRUD_Project.mappers;

import com.CRUD_Project.dto.AuthorDTO;
import com.CRUD_Project.dto.GenreBookDTO;
import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.GenreBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreBookMapper {
    GenreBookMapper INSTANCE = Mappers.getMapper(GenreBookMapper.class);

    @Mapping(target = "name", source = "name")
    GenreBookDTO toDTO(GenreBook genreBook);
    List<GenreBookDTO> toDTOList(List<GenreBook> genreBooks);
    @Mapping(target = "name", source = "name")
    GenreBook toEntity(GenreBookDTO genreBookDTO);
}
