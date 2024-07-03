package com.CRUD_Project.services;

import com.CRUD_Project.entities.*;
import com.CRUD_Project.repositories.BookRepository;
import com.CRUD_Project.repositories.ExtraditionRepository;
import com.CRUD_Project.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraditionService {
    @Autowired
    private ExtraditionRepository extraditionRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public ExtraditionService() {
    }

    public String findExtradition(Integer id) {
        Optional<Extradition> extradition = extraditionRepository.findById(id);
        return extradition.map(value -> "Найдена выдача: книга " + value.getBook().getTitle() + " читатель " + value.getReader().getName() + ", дата выдачи: " + value.getDateIssue() + ", дата возврата: " + value.getDateReturn())
                .orElse("Выдачи с id " + id + " не существует!");
    }

    public List<Extradition> findAll() {
        return extraditionRepository.findAll();
    }

    public String create(Integer bookId, Integer readerId, String dateIssue, String dateReturn) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга с id" + bookId +  "не найдена"));

        Reader reader = readerRepository.findById(readerId)
                .orElseThrow(() -> new RuntimeException("Читатель с id" + readerId +  "не найден"));

        Extradition extradition = Extradition.builder()
                .book(book)
                .reader(reader)
                .dateIssue(dateIssue)
                .dateReturn(dateReturn)
                .build();

        extraditionRepository.save(extradition);
        return "Выдача создана успешно!";
    }

    public String delete(Integer id) {
        Optional<Extradition> extraditionOptional = extraditionRepository.findById(id);
        if (extraditionOptional.isPresent()) {
            extraditionRepository.delete(extraditionOptional.get());
            return "Выдача с id " + id + " была удалена";
        } else return "Выдачи с id " + id + " не существует!";
    }

    public String edit(Integer id, Integer bookId, Integer readerId, String dateIssue, String dateReturn) {
        Optional<Extradition> extraditionOptional = extraditionRepository.findById(id);
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Reader> reader = readerRepository.findById(readerId);

        if (extraditionOptional.isPresent() && book.isPresent() && reader.isPresent()) {
            Extradition extradition = extraditionOptional.get();
            extradition.setBook(book.get());
            extradition.setReader(reader.get());
            extradition.setDateIssue(dateIssue);
            extradition.setDateReturn(dateReturn);
            extraditionRepository.save(extradition);
            return "Выдача с id " + id + " была изменена.";
        } else return "Выдачи с id " + id + " не существует или Книга/Читатель не найдены!";
    }
}
