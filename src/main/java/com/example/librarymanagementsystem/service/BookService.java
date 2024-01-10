package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book) {
        // Add business logic or validation if needed
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book updatedBook) {
        // Add business logic or validation if needed
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            // Update other fields as needed
            return bookRepository.save(existingBook);
        }
        return null; // or throw an exception
    }

    public void deleteBook(Long id) {
        // Add business logic or validation if needed
        bookRepository.deleteById(id);
    }
}
