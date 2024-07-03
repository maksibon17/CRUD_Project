package com.CRUD_Project.services;

import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.mappers.ReaderMapper;
import com.CRUD_Project.repositories.ReaderRepository;
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
    public ResponseEntity<ReaderDTO> findReader(Integer id) {
        Optional<Reader> reader = readerRepository.findById(id);
        return reader.isPresent()
                ? ResponseEntity.ok(ReaderMapper.INSTANCE.toDTO(reader.get()))
                : ResponseEntity.notFound().build();
    }
    public ResponseEntity<List<ReaderDTO>> findAll() {
        List<Reader> readers = readerRepository.findAll();
        List<ReaderDTO> readerDTOList = ReaderMapper.INSTANCE.toDTOList(readers);
        return ResponseEntity.ok(readerDTOList);
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

    public ResponseEntity<String> delete(Integer id) {
        Optional<Reader> existingReader = readerRepository.findById(id);
        if (existingReader.isPresent()) {
            readerRepository.deleteById(id);
            return ResponseEntity.ok("Читатель с id " + id + " успешно удалён.");
        } else {
            return ResponseEntity.status(404).body("Пользователя с id " + id + " не существует!");
        }
    }

    public ResponseEntity<ReaderDTO> edit(Integer id, ReaderDTO readerDTO) {
        Optional<Reader> existingReader = readerRepository.findById(id);
        if (existingReader.isPresent()) {
            Reader reader = existingReader.get();
            reader.setName(readerDTO.name());
            Reader updatedReader = readerRepository.save(reader);
            ReaderDTO updatedReaderDTO = ReaderMapper.INSTANCE.toDTO(updatedReader);
            return ResponseEntity.ok(updatedReaderDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
