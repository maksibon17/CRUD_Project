package com.CRUD_Project.services;

import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.repository.ReaderRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public ReaderService() {
    }
    public String findReader(Integer id) {
        Optional<Reader> reader = readerRepository.findById(id);
        return reader.map(value -> "Найден читатель: " + value.getName() + " с почтой " + value.getEmail())
                .orElse("Пользователя с id " + id + " не существует!");
    }
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public String create(String name, String email) {
        if (readerRepository.findByEmail(email) != null) return "Пользователь с почтой " + email + " уже существует!";
        else {
            Reader reader = new Reader(name, email);
            readerRepository.save(reader);
            return "Пользователь с именем " + name + " и почтой " + email + " создан успешно!";
        }
    }

    public String delete(Integer id) {
        Optional<Reader> readerOptional = readerRepository.findById(id);
        if (readerOptional.isPresent()) {
            readerRepository.delete(readerOptional.get());
            return "Пользователь с id " + id + " был удалён";
        } else return "Пользователя с id " + id + " не существует!";
    }

    public String edit(Integer id, String name) {
        Optional<Reader> readerOptional = readerRepository.findById(id);
        if (readerOptional.isPresent()) {
            readerOptional.get().setName(name);
            readerRepository.save(readerOptional.get());
            return "Пользователь с id " + id + " был изменён. Теперь его зовут " + name;
        } else return "Пользователя с id " + id + " не существует!";
    }
}
