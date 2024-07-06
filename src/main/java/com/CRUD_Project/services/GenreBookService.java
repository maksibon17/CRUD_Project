package com.CRUD_Project.services;

import com.CRUD_Project.dto.GenreBookDTO;
import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.mappers.GenreBookMapper;
import com.CRUD_Project.mappers.ReaderMapper;
import com.CRUD_Project.repositories.GenreBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreBookService {
    @Autowired
    private GenreBookRepository genreBookRepository;

    public GenreBookService() {
    }

    public ResponseEntity<GenreBookDTO> findGenre(Integer id) {
        Optional<GenreBook> genreBook = genreBookRepository.findById(id);
        return genreBook.isPresent()
                ? ResponseEntity.ok(GenreBookMapper.INSTANCE.toDTO(genreBook.get()))
                : ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<GenreBookDTO>> findAll() {
        List<GenreBook> genreBooks = genreBookRepository.findAll();
        List<GenreBookDTO> genreBookDTOListDTOList = GenreBookMapper.INSTANCE.toDTOList(genreBooks);
        return ResponseEntity.ok(genreBookDTOListDTOList);
    }

    public ResponseEntity<?> create(GenreBookDTO genreBookDTO) {
        if (genreBookRepository.findByName(genreBookDTO.name())!=null) {
            return ResponseEntity.badRequest().body("Жанр с названием " + genreBookDTO.name() + " уже существует!");
        } else {
            GenreBook genreBook = GenreBookMapper.INSTANCE.toEntity(genreBookDTO);
            GenreBook savedGenre = genreBookRepository.save(genreBook);
            GenreBookDTO savedGenreDTO = GenreBookMapper.INSTANCE.toDTO(savedGenre);
            return ResponseEntity.ok(savedGenreDTO);
        }
    }

    public ResponseEntity<String> delete(Integer id) {
        Optional<GenreBook> existingGenreBook = genreBookRepository.findById(id);
        if (existingGenreBook.isPresent()) {
            genreBookRepository.deleteById(id);
            return ResponseEntity.ok("Жанр с id " + id + " успешно удалён.");
        } else {
            return ResponseEntity.status(404).body("Жанр с id " + id + " не существует!");
        }
    }

    public ResponseEntity<GenreBookDTO> edit(Integer id, GenreBookDTO genreBookDTO) {
        Optional<GenreBook> existingGenreBook = genreBookRepository.findById(id);
        if (existingGenreBook.isPresent()) {
            GenreBook genreBook = existingGenreBook.get();
            genreBook.setName(genreBookDTO.name());
            GenreBook updatedGenreBook = genreBookRepository.save(genreBook);
            GenreBookDTO updatedGenreBookDTO = GenreBookMapper.INSTANCE.toDTO(updatedGenreBook);
            return ResponseEntity.ok(updatedGenreBookDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}