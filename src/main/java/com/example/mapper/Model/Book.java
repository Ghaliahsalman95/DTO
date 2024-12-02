package com.example.mapper.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Title is mandatory")
    @Column(columnDefinition = "varchar(20) not null ")
    private String title;
    @NotEmpty(message = "Author is mandatory")
    @Column(columnDefinition = "varchar(20) not null ")

    private String author;
    @Positive(message = "Price must be a positive value")
    @Column(columnDefinition = "int not null ")
    private int price;


    // Sensitive Data hide it ( DTO )
    @NotEmpty(message = "ISBN is mandatory")
    private String isbn;

    private String publisherInternalNotes;
}