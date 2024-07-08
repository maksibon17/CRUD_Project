package com.CRUD_Project.services;

import com.CRUD_Project.dto.BookDTO;
import com.CRUD_Project.dto.ExtraditionDTO;
import com.CRUD_Project.entities.*;
import com.CRUD_Project.mappers.ExtraditionMapper;
import com.CRUD_Project.repositories.BookRepository;
import com.CRUD_Project.repositories.ExtraditionRepository;
import com.CRUD_Project.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtraditionService {
    private final ExtraditionRepository extraditionRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public ExtraditionService(ExtraditionRepository extraditionRepository,
                              BookRepository bookRepository,
                              ReaderRepository readerRepository) {
        this.extraditionRepository = extraditionRepository;
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public ResponseEntity<ExtraditionDTO> findExtradition(Integer id) {
        Optional<Extradition> extradition = extraditionRepository.findById(id);
        return extradition.map(value -> ResponseEntity.ok(ExtraditionMapper.INSTANCE.toDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<ExtraditionDTO>> findAll() {
        List<Extradition> extraditions = extraditionRepository.findAll();
        return ResponseEntity.ok(ExtraditionMapper.INSTANCE.toDTOList(extraditions));
    }

    public ResponseEntity<?> create(ExtraditionDTO extraditionDTO) {
        Optional<Book> bookOpt = bookRepository.findById(extraditionDTO.bookId());
        Optional<Reader> readerOpt = readerRepository.findById(extraditionDTO.readerId());

        if (bookOpt.isPresent() && readerOpt.isPresent()) {
            // Проверяем, существует ли уже выдача с такими параметрами
            Optional<Extradition> existingExtraditionOpt = extraditionRepository.
                    findByBookAndReaderAndDateIssueAndDateReturn(
                    bookOpt.get(), readerOpt.get(), extraditionDTO.dateIssue(),extraditionDTO.dateReturn());

            if (existingExtraditionOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Выдача с указанными параметрами уже существует");
            }

            // Создаем новую выдачу
            Extradition extradition = ExtraditionMapper.INSTANCE.toEntity(extraditionDTO);
            extradition.setBook(bookOpt.get());
            extradition.setReader(readerOpt.get());
            Extradition savedExtradition = extraditionRepository.save(extradition);
            ExtraditionDTO savedExtraditionDTO = ExtraditionMapper.INSTANCE.toDTO(savedExtradition);
            return ResponseEntity.ok(savedExtraditionDTO);
        } else {
            return ResponseEntity.badRequest().body("Книга или Читатель с указанным id не существует");
        }
    }

    public ResponseEntity<String> delete(Integer id) {
        if (extraditionRepository.existsById(id)) {
            extraditionRepository.deleteById(id);
            return ResponseEntity.ok("Выдача с id " + id + " успешно удалена.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> edit(Integer id, ExtraditionDTO extraditionDTO) {
        Optional<Extradition> existingExtraditionOpt = extraditionRepository.findById(id);
        if (existingExtraditionOpt.isPresent()) {
            Extradition existingExtradition = existingExtraditionOpt.get();

            Optional<Book> bookOpt = bookRepository.findById(extraditionDTO.bookId());
            Optional<Reader> readerOpt = readerRepository.findById(extraditionDTO.readerId());

            if (bookOpt.isPresent() && readerOpt.isPresent()) {


                existingExtradition.setBook(bookOpt.get());
                existingExtradition.setReader(readerOpt.get());

                // Проверяем, существует ли уже выдача с такими параметрами
                Optional<Extradition> existingExtraditionOpt2 = extraditionRepository.
                        findByBookAndReaderAndDateIssueAndDateReturn(
                        bookOpt.get(), readerOpt.get(), extraditionDTO.dateIssue(),extraditionDTO.dateReturn());

                if (existingExtraditionOpt2.isPresent()) {
                    return ResponseEntity.badRequest().body("Выдача с указанными параметрами уже существует");
                }

                existingExtradition.setDateIssue(extraditionDTO.dateIssue());
                existingExtradition.setDateReturn(extraditionDTO.dateReturn());

                Extradition savedExtradition = extraditionRepository.save(existingExtradition);
                ExtraditionDTO savedExtraditionDTO = ExtraditionMapper.INSTANCE.toDTO(savedExtradition);
                return ResponseEntity.ok(savedExtraditionDTO);
            } else {
                return ResponseEntity.badRequest().body("Книга или Читатель не найден");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
