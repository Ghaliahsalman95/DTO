package com.example.mapper.Controller;

import com.example.mapper.DTO.BookDTO;
import com.example.mapper.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
        //========================= CRUD =======================

    // Get all books
    @GetMapping("get-all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }


    // Add a new book
    @PostMapping("add-book")
    public ResponseEntity addBook(@RequestBody BookDTO bookDTO,
                                  @RequestParam String isbn,
                                  @RequestParam String publisherInternalNotes) {
        bookService.addBook(bookDTO, isbn, publisherInternalNotes);
        return ResponseEntity.ok("Added Successfully");
    }

    @PutMapping("update-book/{id}")
    public ResponseEntity updateBook(@PathVariable Integer id, @RequestBody BookDTO bookDTO,
                                     @RequestParam String isbn,
                                     @RequestParam String publisherInternalNotes) {
        bookService.updateBook(id, bookDTO, isbn, publisherInternalNotes);
        return ResponseEntity.status(HttpStatus.OK).body("Book updated Successfully");
    }


    // Delete a book by ID
    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().body("Deleted Successfully ");
    }
//=================================== Extra Endpoint ================================
    // Endpoint to get book by ISBN
    @GetMapping("/isbn/public/{isbn}")
    public ResponseEntity<BookDTO> findBookByIsbn(@PathVariable String isbn) {
        BookDTO bookDTO = bookService.findBookByIsbn(isbn);
        return ResponseEntity.ok(bookDTO);
    }


    // Get a book by ID
    @GetMapping("/get-book/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }





}
