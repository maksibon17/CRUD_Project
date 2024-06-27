package com.CRUD_Project.controllers;

import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping("/reader/{id}") // поиск читателя по id
    public String findReaderById(@PathVariable(value = "id") Integer id) {
        return readerService.findReader(id);
    }

    @GetMapping("/readers") // вывод всех читателей
    public List<Reader> findAllReaders() {
        return readerService.findAll();
    }

    @PostMapping("/reader/create") // создать читателя
    public String createReader(@RequestParam String name, @RequestParam String email) {
        return readerService.create(name, email);
    }

    @PostMapping("/reader/{id}/edit") //изменения полей читателя
    public String editReader(@PathVariable(value = "id") Integer id, @RequestParam String name){
        return readerService.edit(id,name);
    }

    @PostMapping("/reader/{id}/remove") // удаляем читателя по id
    public String deleteReader(@PathVariable(value = "id") Integer id) {
        return readerService.delete(id);
    }
}
