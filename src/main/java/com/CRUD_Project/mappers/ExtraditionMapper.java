package com.CRUD_Project.mappers;

import com.CRUD_Project.dto.ExtraditionDTO;
import com.CRUD_Project.entities.Extradition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Mapper
public interface ExtraditionMapper {
    ExtraditionMapper INSTANCE = Mappers.getMapper(ExtraditionMapper.class);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "readerId", source = "reader.id")
    ExtraditionDTO toDTO(Extradition extradition);

    List<ExtraditionDTO> toDTOList(List<Extradition> extraditions);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "reader", ignore = true)
    Extradition toEntity(ExtraditionDTO extraditionDTO);

    default Date mapLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    default LocalDate mapDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
