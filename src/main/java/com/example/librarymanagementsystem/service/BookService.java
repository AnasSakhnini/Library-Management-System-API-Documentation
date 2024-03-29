package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.exception.CustomException;
import com.example.librarymanagementsystem.projection.BookProjection;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.validation.BookPartialUpdateRequest;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<BookProjection> getAllBooks() {
        return bookRepository.getAllBy();
    }
    public BookProjection getBookById(Long id) {
        Optional<BookProjection> optionalBook = bookRepository.getBookById(id);
        if(optionalBook.isEmpty()){
            throw new CustomException("Book not found with ID: " + id);
        }
        return optionalBook.get();
    }
    public void addBook(Book book) {
        // Add business logic or validation if needed
        bookRepository.save(book);
    }
    public void updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationYear(updatedBook.getPublicationYear());
            book.setIsbn(updatedBook.getIsbn());
            bookRepository.save(book);
        } else {
            throw new CustomException("Book not found with ID: " + id);
        }

    }
    public void updateBookPartially(Long id, BookPartialUpdateRequest updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            // Update book's attributes based on the update request
            if (updatedBook.getTitle() != null) {
                book.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthor() != null) {
                book.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getPublicationYear() != null) {
                book.setPublicationYear(updatedBook.getPublicationYear());
            }
            if (updatedBook.getIsbn() != null) {
                book.setIsbn(updatedBook.getIsbn());
            }
            bookRepository.save(book);
        } else {
            throw new CustomException("Book not found with ID: " + id);
        }
    }
    public void deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            bookRepository.delete(book);
        } else {
            throw new CustomException("Book not found with ID: " + id);
        }
    }
}
