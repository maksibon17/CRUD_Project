package com.CRUD_Project.controllers;

import com.CRUD_Project.entities.Extradition;
import com.CRUD_Project.services.ExtraditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExtraditionController {
    @Autowired
    private ExtraditionService extraditionService;

    @GetMapping("/extradition/{id}") // поиск выдачи по id
    public String findExtraditionById(@PathVariable(value = "id") Integer id) {
        return extraditionService.findExtradition(id);
    }

    @GetMapping("/extraditions") // вывод всех выдач
    public List<Extradition> findAllExtraditions() {
        return extraditionService.findAll();
    }

    @PostMapping("/extradition/create") // создать выдачу
    public String createExtradition(@RequestParam Integer bookId, @RequestParam Integer readerId, @RequestParam String dateIssue, @RequestParam String dateReturn) {
        return extraditionService.create(bookId, readerId, dateIssue, dateReturn);
    }

    @PostMapping("/extradition/{id}/edit") // изменение полей выдачи
    public String editExtradition(@PathVariable(value = "id") Integer id, @RequestParam Integer bookId, @RequestParam Integer readerId, @RequestParam String dateIssue, @RequestParam String dateReturn) {
        return extraditionService.edit(id, bookId, readerId, dateIssue, dateReturn);
    }

    @PostMapping("/extradition/{id}/remove") // удаление выдачи по id
    public String deleteExtradition(@PathVariable(value = "id") Integer id) {
        return extraditionService.delete(id);
    }
}
