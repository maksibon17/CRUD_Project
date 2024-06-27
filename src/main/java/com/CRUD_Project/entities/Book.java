package com.CRUD_Project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author idAuthor;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GenreBook idGenre;

    public Book(String name, Author idAuthor, GenreBook idGenre) {
        this.name = name;
        this.idAuthor = idAuthor;
        this.idGenre = idGenre;
    }
}
