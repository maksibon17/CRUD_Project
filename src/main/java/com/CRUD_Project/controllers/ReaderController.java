package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.services.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
@Tag(name = "Читатели",
     description = "Операции, связанные с читателями")
public class ReaderController {

    private final ReaderService readerService;

    public ReaderController(ReaderService readerService) {
        this.readerService = readerService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех читателей",
               description = "Возвращает список всех читателей в системе")
    public ResponseEntity<List<ReaderDTO>> findAllReaders() {
        return readerService.findAll();
    }

    @PostMapping("/create")
    @Operation(summary = "Создать читателя",
               description = "Создает нового читателя в системе на основе предоставленного объекта ReaderDTO")
    public ResponseEntity<?> createReader(
            @Parameter(description = "Объект ReaderDTO для создания нового читателя", required = true)
            @RequestBody ReaderDTO readerDTO) {
        return readerService.create(readerDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти читателя по ID",
               description = "Возвращает информацию о читателе с указанным ID")
    public ResponseEntity<ReaderDTO> findReaderById(
            @Parameter(description = "ID читателя", required = true)
            @PathVariable(value = "id") Integer id) {
        return readerService.findReader(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию о читателе",
               description = "Обновляет информацию о читателе с указанным ID на основе предоставленного объекта ReaderDTO")
    public ResponseEntity<ReaderDTO> editReader(
            @Parameter(description = "ID читателя", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Обновленная информация о читателе", required = true)
            @RequestBody ReaderDTO readerDTO) {
        return readerService.edit(id, readerDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить читателя", description = "Удаляет читателя с указанным ID из системы")
    public ResponseEntity<String> deleteReader(
            @Parameter(description = "ID читателя", required = true)
            @PathVariable(value = "id") Integer id) {
        return readerService.delete(id);
    }
}
