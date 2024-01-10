package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Borrowing;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    public void borrowBook(Book book, Patron patron) {
        // Add business logic or validation if needed
        Borrowing borrowing = new Borrowing();
        borrowing.setBook(book);
        borrowing.setPatron(patron);
        borrowing.setBorrowedDate(LocalDate.now());
        // Set other borrowing details
        borrowingRepository.save(borrowing);
    }

    public void returnBook(Long bookId, Long patronId) {
        // Add business logic or validation if needed
        Borrowing borrowing = borrowingRepository.findByBookIdAndPatronIdAndReturnDateIsNull(bookId, patronId);
        if (borrowing != null) {
            borrowing.setReturnDate(LocalDate.now());
            // Set other return details
            borrowingRepository.save(borrowing);
        }
        // Optionally, handle the case when the book was not borrowed or already returned
    }
}
