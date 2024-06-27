package com.CRUD_Project.services;

import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.Book;
import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.repository.AuthorRepository;
import com.CRUD_Project.repository.BookRepository;
import com.CRUD_Project.repository.GenreBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreBookRepository genreBookRepository;

    public BookService() {
    }

    public String findBook(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> "Найдена книга: " + value.getName() + ", автор: " + value.getIdAuthor().getName() + ", жанр: " + value.getIdGenre().getName())
                .orElse("Книги с id " + id + " не существует!");
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public String create(String name, Integer authorId, Integer genreId) {
        Optional<Author> author = authorRepository.findById(authorId);
        Optional<GenreBook> genre = genreBookRepository.findById(genreId);

        if (author.isPresent() && genre.isPresent()) {
            Book book = new Book();
            book.setName(name);
            book.setIdAuthor(author.get());
            book.setIdGenre(genre.get());
            bookRepository.save(book);
            return "Книга с названием " + name + " создана успешно!";
        } else {
            return "Автор или жанр не найдены!";
        }
    }

    public String delete(Integer id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
            return "Книга с id " + id + " была удалена";
        } else return "Книги с id " + id + " не существует!";
    }

    public String edit(Integer id, String name) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setName(name);
            bookRepository.save(book);
            return "Книга с id " + id + " была изменена. Теперь её название " + name;
        } else return "Книги с id " + id + " не существует!";
    }
}