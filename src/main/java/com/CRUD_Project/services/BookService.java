package com.CRUD_Project.services;

import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.Book;
import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.repositories.AuthorRepository;
import com.CRUD_Project.repositories.BookRepository;
import com.CRUD_Project.repositories.GenreBookRepository;
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
        return book.map(value -> "Найдена книга: " + value.getTitle() + ", автор: " + value.getAuthor().getName() + ", жанр: " + value.getGenreBook().getName())
                .orElse("Книги с id " + id + " не существует!");
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public String create(String title, Integer authorId, Integer genreId) {
        // Получаем автора и жанр из базы данных по их идентификаторам
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Книга с id" + authorId +  "не найдена"));

        GenreBook genreBook = genreBookRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Автоор с id" + genreId +  "не найден"));

        Book book = Book.builder()
                .title(title)
                .author(author)
                .genreBook(genreBook)
                .build();

        bookRepository.save(book);
        return "Книга под названием " + title + "создана";
    }
    public String delete(Integer id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            bookRepository.delete(bookOptional.get());
            return "Книга с id " + id + " была удалена";
        } else return "Книги с id " + id + " не существует!";
    }

    public String edit(Integer id, String title) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(title);
            bookRepository.save(book);
            return "Книга с id " + id + " была изменена. Теперь её название " + title;
        } else return "Книги с id " + id + " не существует!";
    }
}