package com.CRUD_Project.services;

import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.mappers.ReaderMapper;
import com.CRUD_Project.repository.ReaderRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> create(ReaderDTO readerDTO) {
        if (readerRepository.findByEmail(readerDTO.email()) != null) {
            return ResponseEntity.badRequest().body("Пользователь с почтой " + readerDTO.email() + " уже существует!");
        } else {
            Reader reader = ReaderMapper.INSTANCE.toEntity(readerDTO);
            Reader savedReader = readerRepository.save(reader);
            ReaderDTO savedReaderDTO = ReaderMapper.INSTANCE.toDTO(savedReader);
            return ResponseEntity.ok(savedReaderDTO);
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
