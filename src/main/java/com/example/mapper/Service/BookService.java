package com.example.mapper.Service;

import com.example.mapper.DTO.BookDTO;
import com.example.mapper.Model.Book;
import com.example.mapper.Repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    // 1- Retrieve all books as DTOs
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            BookDTO bookDTO = new BookDTO(book.getTitle(), book.getAuthor(), book.getPrice());
            bookDTOs.add(bookDTO);
        }
        return bookDTOs;
    }

    // 2- Add a new book
    public void addBook(BookDTO bookDTO, String isbn, String publisherInternalNotes) {
        Book book = new Book(
                null,                           // ID (auto-generated)
                bookDTO.getTitle(),                // Book title from the DTO
                bookDTO.getAuthor(),               // Book author from the DTO
                bookDTO.getPrice(),                // Book price from the DTO
                isbn,                              // ISBN provided as a parameter
                publisherInternalNotes             // Publisher's internal notes provided as a parameter
        );
        // Save the new Book object to the database.
        bookRepository.save(book);

    }

    // 3- Update a book by ID

    public void updateBook(Integer id, BookDTO bookDTO, String isbn, String publisherInternalNotes) {
        // Retrieve the existing Book object from the database using the provided ID.
        Book book = bookRepository.findBookById(id);

        // Check if the book exists.
        // If not, throw an exception with an appropriate message.
        if (book == null)
            throw new RuntimeException("Book not found");

        // Update the book's properties with the new values from the provided parameters and DTO.
        book.setAuthor(bookDTO.getAuthor());               // Update the author from the DTO.
        book.setTitle(bookDTO.getTitle());                 // Update the title from the DTO.
        book.setPublisherInternalNotes(publisherInternalNotes); // Update the publisher's internal notes.
        book.setIsbn(isbn);                                // Update the ISBN.

        // Save the updated Book object back to the database.
        bookRepository.save(book);
    }

    // 4- Delete a book by ID
    public void deleteBook(Integer id) {
        Book book = bookRepository.findBookById(id);
        if (book == null)
            throw new RuntimeException("Book not found");
        bookRepository.delete(book);
    }

    //==========================================================================


    // Get a book by ID
    public BookDTO getBookById(Integer id) {
        Book book = bookRepository.findBookById(id);
        if (book == null) {
            throw new RuntimeException("Book not found");
        } else {
            return new BookDTO(book.getTitle(), book.getAuthor(), book.getPrice());
        }
    }


    // get Book by Isbn

    public BookDTO findBookByIsbn(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        if (book == null) {
            throw new RuntimeException("Book not found");
        } else {
            return new BookDTO(book.getTitle(), book.getAuthor(), book.getPrice());
        }
    }


    //========================================================================================================
    // Convert Book entity to DTO
    private BookDTO convertToDTO(Book book) {

        return new BookDTO(book.getTitle(), book.getAuthor(), book.getPrice());
    }

    // Convert DTO to Book entity
    private Book convertToEntity(BookDTO bookDTO, String isbn, String publisherInternalNotes) {
        return new Book(null, bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getPrice(), isbn, publisherInternalNotes);
    }
}

