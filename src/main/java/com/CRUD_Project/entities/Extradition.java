package com.CRUD_Project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Extradition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Book idBook;
    private Reader idReader;
    private String dateIssue; //дата выдачи
    private String dateReturn; //дата возврата
}

