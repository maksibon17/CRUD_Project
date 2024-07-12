package com.CRUD_Project.services;

import com.CRUD_Project.dto.AuthorDTO;
import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.Reader;
import com.CRUD_Project.mappers.AuthorMapper;
import com.CRUD_Project.mappers.ReaderMapper;
import com.CRUD_Project.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public ResponseEntity<AuthorDTO> getAuthorById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.map(value -> ResponseEntity.ok(AuthorMapper.INSTANCE.toDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<List<AuthorDTO>> gelAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTOList = AuthorMapper.INSTANCE.toDTOList(authors);
        return ResponseEntity.ok(authorDTOList);
    }

    public ResponseEntity<AuthorDTO> createAuthor(AuthorDTO authorDTO) {
        Author author = AuthorMapper.INSTANCE.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        AuthorDTO savedAuthorDTO = AuthorMapper.INSTANCE.toDTO(savedAuthor);
        return ResponseEntity.ok(savedAuthorDTO);
    }

    public ResponseEntity<String> deleteAuthor(Integer id) {
        Optional<Author> existingReader = authorRepository.findById(id);
        if (existingReader.isPresent()) {
            authorRepository.deleteById(id);
            return ResponseEntity.ok("Автор с id " + id + " успешно удалён.");
        } else {
            return ResponseEntity.status(404).body("Пользователя с id " + id + " не существует!");
        }
    }

    public ResponseEntity<AuthorDTO> updateAuthor(Integer id, AuthorDTO authorDTO) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if (existingAuthor.isPresent()) {
            Author author = existingAuthor.get();
            author.setName(authorDTO.name());
            author.setSurname(authorDTO.surname());
            Author updatedAuthor = authorRepository.save(author);
            AuthorDTO updatedAuthorDTO = AuthorMapper.INSTANCE.toDTO(updatedAuthor);
            return ResponseEntity.ok(updatedAuthorDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
