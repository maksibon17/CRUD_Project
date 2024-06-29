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
public class Extradition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book idBook;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader idReader;

    @Setter
    @Getter
    private String dateIssue; // дата выдачи
    @Setter
    @Getter
    private String dateReturn; // дата возврата

    public Extradition(Book idBook, Reader idReader, String dateIssue, String dateReturn) {
        this.idBook = idBook;
        this.idReader = idReader;
        this.dateIssue = dateIssue;
        this.dateReturn = dateReturn;
    }
    public Extradition(){}

    public Book getIdBook() {
        return idBook;
    }

    public void setIdBook(Book idBook) {
        this.idBook = idBook;
    }

    public Reader getIdReader() {
        return idReader;
    }

    public void setIdReader(Reader idReader) {
        this.idReader = idReader;
    }

    public String getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(String dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

}
