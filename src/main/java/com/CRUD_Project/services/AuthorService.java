package com.CRUD_Project.services;

import com.CRUD_Project.entities.Author;
import com.CRUD_Project.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorService() {
    }

    public String findAuthor(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(value -> "Найден автор: " + value.getName() + " " + value.getSurname())
                .orElse("Автора с id " + id + " не существует!");
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public String create(String name, String surname, String dateOfBirthday) {
        Author author = new Author(name, surname, dateOfBirthday);
        authorRepository.save(author);
        return "Автор с именем " + name + " и фамилией " + surname + " создан успешно!";
    }

    public String delete(Integer id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            authorRepository.delete(authorOptional.get());
            return "Автор с id " + id + " был удалён";
        } else return "Автора с id " + id + " не существует!";
    }

    public String edit(Integer id, String name, String surname, String dateOfBirthday) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            author.setName(name);
            author.setSurname(surname);
            authorRepository.save(author);
            return "Автор с id " + id + " был изменён. Теперь его зовут " + name + " " + surname + ", дата рождения: " + dateOfBirthday;
        } else return "Автора с id " + id + " не существует!";
    }
}
