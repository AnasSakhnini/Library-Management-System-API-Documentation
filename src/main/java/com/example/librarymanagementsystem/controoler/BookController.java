package com.example.librarymanagementsystem.controoler;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.exception.CustomException;
import com.example.librarymanagementsystem.repository.BookRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") @NotBlank Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            throw new CustomException("Book not found with ID: " + id);
        }
        return ResponseEntity.ok(optionalBook.get());
    }

    @PostMapping
    public Book addBook(@RequestBody @Valid Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable("id") @NotBlank @NotNull @Positive(message = "ID must be a positive number") Long id,
            @RequestBody @Valid Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            bookRepository.save(book);
            return ResponseEntity.ok(book);
        } else {
                throw new CustomException("Book not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.delete(book);
            return ResponseEntity.noContent().build();
        } else {
            throw new CustomException("Book not found with ID: " + id);
        }
    }
}
