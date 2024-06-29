package com.CRUD_Project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // исправленный импорт
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String surname;
    @Setter
    @Getter
    private String dateOfBirthday;

    public Author(String name, String surname, String dateOfBirthday) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthday = dateOfBirthday;
    }
    public Author(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }
}
