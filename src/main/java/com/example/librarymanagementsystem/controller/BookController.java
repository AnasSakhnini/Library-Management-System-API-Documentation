package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.projection.BookProjection;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.validation.BookPartialUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookProjection> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookProjection> getBookById(@PathVariable("id")  @PositiveOrZero(message = "ID is less than 0, it should be a positive number") long id) {
        BookProjection book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Object> addBook(
            @RequestBody @Valid Book book) {
        bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(
            @PathVariable("id")  @PositiveOrZero(message = "ID is less than 0, it should be a positive number") long id,
            @RequestBody @Valid Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateBookPartially(
            @PathVariable("id")  @PositiveOrZero(message = "ID is less than 0, it should be a positive number") long id,
            @RequestBody @Valid BookPartialUpdateRequest updatedBook) {
        bookService.updateBookPartially(id, updatedBook);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable("id") @Positive(message = "ID is less than 0, it should be a positive number")  @NotNull Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
