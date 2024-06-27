package com.CRUD_Project.services;

import com.CRUD_Project.entities.Book;
import com.CRUD_Project.entities.Extradition;
import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.repository.BookRepository;
import com.CRUD_Project.repository.ExtraditionRepository;
import com.CRUD_Project.repository.ReaderRepository;
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
        return extradition.map(value -> "Найдена выдача: книга " + value.getIdBook().getName() + " читатель " + value.getIdReader().getName() + ", дата выдачи: " + value.getDateIssue() + ", дата возврата: " + value.getDateReturn())
                .orElse("Выдачи с id " + id + " не существует!");
    }

    public List<Extradition> findAll() {
        return extraditionRepository.findAll();
    }

    public String create(Integer bookId, Integer readerId, String dateIssue, String dateReturn) {
        Optional<Book> book = bookRepository.findById(bookId);
        Optional<Reader> reader = readerRepository.findById(readerId);

        if (book.isPresent() && reader.isPresent()) {
            Extradition extradition = new Extradition(book.get(), reader.get(), dateIssue, dateReturn);
            extraditionRepository.save(extradition);
            return "Выдача создана успешно!";
        } else {
            return "Книга или Читатель не найдены!";
        }
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
            extradition.setIdBook(book.get());
            extradition.setIdReader(reader.get());
            extradition.setDateIssue(dateIssue);
            extradition.setDateReturn(dateReturn);
            extraditionRepository.save(extradition);
            return "Выдача с id " + id + " была изменена.";
        } else return "Выдачи с id " + id + " не существует или Книга/Читатель не найдены!";
    }
}
