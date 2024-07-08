package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping // вывод всех читателей
    public ResponseEntity<List<ReaderDTO>> findAllReaders() {
        return readerService.findAll();
    }

    @PostMapping("/create") // создать читателя
    public ResponseEntity<?> createReader(@RequestBody ReaderDTO readerDTO ) {
        return readerService.create(readerDTO);
    }

    @GetMapping("/{id}") // поиск читателя по id
    public ResponseEntity<ReaderDTO> findReaderById(@PathVariable(value = "id") Integer id) {
        return readerService.findReader(id);
    }

    @PutMapping("/{id}") //изменения полей читателя
    public ResponseEntity<ReaderDTO> editReader(@PathVariable Integer id,
                                                @RequestBody ReaderDTO readerDTO){
        return readerService.edit(id,readerDTO);
    }

    @DeleteMapping("/{id}") // удаляем читателя по id
    public ResponseEntity<String> deleteReader(@PathVariable(value = "id") Integer id) {
        return readerService.delete(id);
    }
}
