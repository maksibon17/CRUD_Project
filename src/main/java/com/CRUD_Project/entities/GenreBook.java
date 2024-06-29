package com.CRUD_Project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class GenreBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Setter
    @Getter
    private String name; // название жанра

    @Setter
    @Getter
    @OneToMany // указывает на поле idGenre в Book
    private List<Book> books; // список книг этого жанра

    public GenreBook(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }
    public GenreBook(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
