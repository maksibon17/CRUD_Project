package com.CRUD_Project.mappers;

import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Reader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReaderMapper {
    ReaderMapper INSTANCE = Mappers.getMapper(ReaderMapper.class);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    ReaderDTO toDTO(Reader reader);
    List<ReaderDTO> toDTOList(List<Reader> readers);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    Reader toEntity(ReaderDTO readerDTO);
}
