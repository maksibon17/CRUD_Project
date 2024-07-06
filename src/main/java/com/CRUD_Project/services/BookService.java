package com.CRUD_Project.services;

import com.CRUD_Project.dto.BookDTO;
import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.Book;
import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.mappers.BookMapper;
import com.CRUD_Project.repositories.AuthorRepository;
import com.CRUD_Project.repositories.BookRepository;
import com.CRUD_Project.repositories.GenreBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<BookDTO> findBook(Integer id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(value -> ResponseEntity.ok(BookMapper.INSTANCE.toDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(BookMapper.INSTANCE.toDTOList(books));
    }

    public ResponseEntity<?> create(BookDTO bookDTO) {
        Optional<Author> authorOpt = authorRepository.findById(bookDTO.authorId());
        Optional<GenreBook> genreBookOpt = genreBookRepository.findById(bookDTO.genreBookId());

        if (authorOpt.isPresent() && genreBookOpt.isPresent()) {
            Author author = authorOpt.get();
            GenreBook genreBook = genreBookOpt.get();

            // Проверка на существование книги с таким же названием, автором и жанром
            if (!checkOnExistenceBook(bookDTO, author, genreBook)) {
                // Создание новой книги
                Book book = BookMapper.INSTANCE.toEntity(bookDTO);
                book.setAuthor(author);
                book.setGenreBook(genreBook);
                Book savedBook = bookRepository.save(book);
                BookDTO savedBookDTO = BookMapper.INSTANCE.toDTO(savedBook);
                return ResponseEntity.ok(savedBookDTO);
            } else return ResponseEntity.badRequest().body("Книга с таким названием, автором и жанром уже существует");


        } else {
            return ResponseEntity.badRequest().body("Автор или Жанр с указанным id не существует");
        }
    }

    public ResponseEntity<String> delete(Integer id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok("Книга с id "+ id + " успешно удалена.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> edit(Integer id, BookDTO bookDTO) {
        Optional<Book> existingBookOpt = bookRepository.findById(id);
        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();

            // Проверяем существование автора и жанра и устанавливаем их
            Optional<Author> authorOpt = authorRepository.findById(bookDTO.authorId());
            Optional<GenreBook> genreBookOpt = genreBookRepository.findById(bookDTO.genreBookId());

            if (authorOpt.isPresent() && genreBookOpt.isPresent()) {
                Author author = authorOpt.get();
                GenreBook genreBook = genreBookOpt.get();

                // Проверка на существование книги с таким же названием, автором и жанром
                if (!checkOnExistenceBook(bookDTO, author, genreBook)) {

                    // Обновляем данные книги
                    existingBook.setTitle(bookDTO.title());
                    existingBook.setAuthor(author);
                    existingBook.setGenreBook(genreBook);

                    // Сохраняем изменения
                    Book savedBook = bookRepository.save(existingBook);

                    // Преобразуем сохраненную книгу обратно в DTO
                    BookDTO savedBookDTO = BookMapper.INSTANCE.toDTO(savedBook);
                    return ResponseEntity.ok(savedBookDTO);
                } else {
                    return ResponseEntity.badRequest().body("Книга с таким названием, автором и жанром уже существует");
                }
            } else {
                return ResponseEntity.badRequest().body("Автор или жанр не найден");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Boolean checkOnExistenceBook(BookDTO bookDTO, Author author, GenreBook genreBook) {
        Optional<Book> duplicateBookOpt = bookRepository.
                findByTitleAndAuthorAndGenreBook(bookDTO.title(), author, genreBook);
        return duplicateBookOpt.isPresent();
    }
}