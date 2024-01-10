package com.example.librarymanagementsystem.controoler;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Borrowing;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.BorrowingRepository;
import com.example.librarymanagementsystem.repository.PatronRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrow")
@Validated
public class BorrowingController {
    private final BorrowingRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public BorrowingController(BorrowingRepository borrowingRecordRepository,
                               BookRepository bookRepository, PatronRepository patronRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Borrowing> borrowBook(
            @PathVariable("bookId")  @NotBlank @NotNull @Positive(message = "bookId must be a positive number")  Long bookId,
            @PathVariable("patronId") @NotNull @NotBlank @Positive(message = "patronId must be a positive number") Long patronId) {
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
                borrowingRecordRepository.save(borrowing);

                return ResponseEntity.ok(borrowing);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Borrowing> returnBook(
            @PathVariable("bookId") @NotBlank @NotNull @Positive(message = "bookId must be a positive number") Long bookId,
            @PathVariable("patronId") @NotBlank @NotNull @Positive(message = "patronId must be a positive number") Long patronId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Optional<Patron> optionalPatron = patronRepository.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            Optional<Borrowing> optionalBorrowingRecord = borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
            if (optionalBorrowingRecord.isPresent()) {
                Borrowing borrowing = optionalBorrowingRecord.get();
                borrowing.setReturnDate(LocalDate.now());
                borrowingRecordRepository.save(borrowing);

                return ResponseEntity.ok(borrowing);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean bookAvailableForBorrowing(Book book) {
        List<Borrowing> borrowingRecords = borrowingRecordRepository.findByBookAndReturnDateIsNull(book);
        int maxBorrowingLimit = 3; // Set the maximum borrowing limit as per your requirement

        // Check if the book is available for borrowing based on your custom logic
        return borrowingRecords.size() < maxBorrowingLimit;
    }

    private boolean patronCanBorrow(Patron patron) {
        List<Borrowing> borrowingRecords = borrowingRecordRepository.findByPatronAndReturnDateIsNull(patron);
        int maxBorrowingLimit = 3; // Set the maximum borrowing limit as per your requirement

        // Check if the patron is eligible to borrow a book based on your custom logic
        return borrowingRecords.size() < maxBorrowingLimit;
    }
}