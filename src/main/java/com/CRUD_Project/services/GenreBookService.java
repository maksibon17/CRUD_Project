package com.CRUD_Project.services;

import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.repository.GenreBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreBookService {
    @Autowired
    private GenreBookRepository genreBookRepository;

    public GenreBookService() {
    }

    public String findGenre(Integer id) {
        Optional<GenreBook> genre = genreBookRepository.findById(id);
        return genre.map(value -> "Найден жанр: " + value.getName())
                .orElse("Жанра с id " + id + " не существует!");
    }

    public List<GenreBook> findAll() {
        return genreBookRepository.findAll();
    }

    public String create(String name) {
        GenreBook genre = new GenreBook(name, null);
        genreBookRepository.save(genre);
        return "Жанр с названием " + name + " создан успешно!";
    }

    public String delete(Integer id) {
        Optional<GenreBook> genreOptional = genreBookRepository.findById(id);
        if (genreOptional.isPresent()) {
            genreBookRepository.delete(genreOptional.get());
            return "Жанр с id " + id + " был удалён";
        } else return "Жанра с id " + id + " не существует!";
    }

    public String edit(Integer id, String name) {
        Optional<GenreBook> genreOptional = genreBookRepository.findById(id);
        if (genreOptional.isPresent()) {
            genreOptional.get().setName(name);
            genreBookRepository.save(genreOptional.get());
            return "Жанр с id " + id + " был изменён. Теперь его название " + name;
        } else return "Жанра с id " + id + " не существует!";
    }
}