package com.CRUD_Project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // исправленный импорт
import lombok.*;
//Макс лох

@Entity
@Data
@Builder
@AllArgsConstructor
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String email;

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public Reader() {}
    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
