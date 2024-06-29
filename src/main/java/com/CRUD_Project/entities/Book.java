package com.CRUD_Project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author idAuthor;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreBook idGenre;

    public Book(String name, Author idAuthor, GenreBook idGenre) {
        this.name = name;
        this.idAuthor = idAuthor;
        this.idGenre = idGenre;
    }
    public Book(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(Author idAuthor) {
        this.idAuthor = idAuthor;
    }

    public GenreBook getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(GenreBook idGenre) {
        this.idGenre = idGenre;
    }

}
