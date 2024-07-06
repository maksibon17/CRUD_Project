package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.ExtraditionDTO;
import com.CRUD_Project.services.ExtraditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/extradition")
public class ExtraditionController {
    @Autowired
    private ExtraditionService extraditionService;

    @GetMapping("/{id}") // поиск выдачи по id
    public ResponseEntity<ExtraditionDTO> findExtraditionById(@PathVariable(value = "id") Integer id) {
        return extraditionService.findExtradition(id);
    }

    @GetMapping("/all") // вывод всех выдач
    public ResponseEntity<List<ExtraditionDTO>> findAllExtraditions() {
        return extraditionService.findAll();
    }

    @PostMapping("/create") // создать выдачу
    public ResponseEntity<?> createExtradition(@RequestBody ExtraditionDTO extraditionDTO) {
        return extraditionService.create(extraditionDTO);
    }

    @PutMapping("/{id}/edit") // изменение полей выдачи
    public ResponseEntity<?> editExtradition(@PathVariable(value = "id") Integer id, @RequestBody ExtraditionDTO extraditionDTO) {
        return extraditionService.edit(id, extraditionDTO);
    }

    @DeleteMapping("/{id}/remove") // удаление выдачи по id
    public ResponseEntity<String> deleteExtradition(@PathVariable(value = "id") Integer id) {
        return extraditionService.delete(id);
    }
}
