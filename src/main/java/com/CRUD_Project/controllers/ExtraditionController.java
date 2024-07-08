package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.ExtraditionDTO;
import com.CRUD_Project.services.ExtraditionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extraditions")
@Tag(name = "Выдачи",
     description = "Операции, связанные с выдачами книг")
public class ExtraditionController {

    private final ExtraditionService extraditionService;

    public ExtraditionController(ExtraditionService extraditionService) {
        this.extraditionService = extraditionService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех выдач",
               description = "Возвращает список всех выдач в системе")
    public ResponseEntity<List<ExtraditionDTO>> findAllExtraditions() {
        return extraditionService.findAll();
    }

    @PostMapping("/create")
    @Operation(summary = "Создать выдачу",
               description = "Создает новую выдачу в системе на основе предоставленного объекта ExtraditionDTO")
    public ResponseEntity<?> createExtradition(
            @Parameter(description = "Объект ExtraditionDTO для создания новой выдачи", required = true)
            @RequestBody ExtraditionDTO extraditionDTO) {
        return extraditionService.create(extraditionDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти выдачу по ID",
               description = "Возвращает информацию о выдаче с указанным ID")
    public ResponseEntity<ExtraditionDTO> findExtraditionById(
            @Parameter(description = "ID выдачи", required = true)
            @PathVariable(value = "id") Integer id) {
        return extraditionService.findExtradition(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию о выдаче",
               description = "Обновляет информацию о выдаче с указанным ID на основе предоставленного " +
                       "объекта ExtraditionDTO")
    public ResponseEntity<?> editExtradition(
            @Parameter(description = "ID выдачи", required = true)
            @PathVariable(value = "id") Integer id,
            @Parameter(description = "Обновленная информация о выдаче", required = true)
            @RequestBody ExtraditionDTO extraditionDTO) {
        return extraditionService.edit(id, extraditionDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить выдачу",
               description = "Удаляет выдачу с указанным ID из системы")
    public ResponseEntity<String> deleteExtradition(
            @Parameter(description = "ID выдачи", required = true)
            @PathVariable(value = "id") Integer id) {
        return extraditionService.delete(id);
    }
}
