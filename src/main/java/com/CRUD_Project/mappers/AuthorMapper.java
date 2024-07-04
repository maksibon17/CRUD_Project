package com.CRUD_Project.mappers;

import com.CRUD_Project.dto.AuthorDTO;
import com.CRUD_Project.entities.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    AuthorDTO toDTO(Author author);

    List<AuthorDTO> toDTOList(List<Author> authors);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    Author toEntity(AuthorDTO authorDTO);
}