package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Borrowing;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exception.CustomException;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.BorrowingRepository;
import com.example.librarymanagementsystem.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    @Autowired
    public BorrowingService(BorrowingRepository borrowingRepository, BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRepository = borrowingRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public Borrowing borrowBook(Long bookId, Long patronId) {
        // Add business logic or validation if needed
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            if (bookAvailableForBorrowing(book) && patronCanBorrow(patron)) {
                Borrowing borrowing = new Borrowing();
                borrowing.setBook(book);
                borrowing.setPatron(patron);
                borrowing.setBorrowedDate(LocalDate.now());
                borrowingRepository.save(borrowing);

                return borrowing;
            } else {
                throw new CustomException("Patron can not borrow this book now");
            }
        } else {
            throw new CustomException("Book ID and Patron ID both should be valid");
        }
    }

    public Borrowing returnBook(Long bookId, Long patronId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            Optional<Borrowing> optionalBorrowingRecord = borrowingRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
            if (optionalBorrowingRecord.isPresent()) {
                Borrowing borrowing = optionalBorrowingRecord.get();
                borrowing.setReturnDate(LocalDate.now());
                borrowingRepository.save(borrowing);
                return borrowing;
            } else {
                throw new CustomException(String.format("No borrowing record for this book ID %d from patron %s", bookId, patronId));
            }
        } else {
            throw new CustomException("Book ID and Patron ID both should be valid");
        }
    }

    private boolean bookAvailableForBorrowing(Book book) {
        List<Borrowing> borrowingRecords = borrowingRepository.findByBookAndReturnDateIsNull(book);
        int maxBorrowingLimit = 3; // Set the maximum borrowing limit as per your requirement

        // Check if the book is available for borrowing based on your custom logic
        return borrowingRecords.size() < maxBorrowingLimit;
    }

    private boolean patronCanBorrow(Patron patron) {
        List<Borrowing> borrowingRecords = borrowingRepository.findByPatronAndReturnDateIsNull(patron);
        int maxBorrowingLimit = 3; // Set the maximum borrowing limit as per your requirement

        // Check if the patron is eligible to borrow a book based on your custom logic
        return borrowingRecords.size() < maxBorrowingLimit;
    }
}
