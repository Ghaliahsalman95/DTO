package com.example.mapper.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mapper.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findBookById(Integer id);
    Book findBookByIsbn(String isbn);
}

