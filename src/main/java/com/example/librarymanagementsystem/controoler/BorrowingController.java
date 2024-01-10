package com.example.librarymanagementsystem.controoler;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Borrowing;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.BorrowingRepository;
import com.example.librarymanagementsystem.repository.PatronRepository;
import com.example.librarymanagementsystem.service.BookService;
import com.example.librarymanagementsystem.service.BorrowingService;
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
    final BorrowingService borrowingService;

    public BorrowingController(BorrowingRepository borrowingRecordRepository,
                               BookRepository bookRepository, PatronRepository patronRepository, BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Borrowing> borrowBook(
            @PathVariable("bookId")  @NotBlank @NotNull @Positive(message = "bookId must be a positive number")  Long bookId,
            @PathVariable("patronId") @NotNull @NotBlank @Positive(message = "patronId must be a positive number") Long patronId) {
        Borrowing borrowing = borrowingService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(borrowing);
    }

    @PutMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Borrowing> returnBook(
            @PathVariable("bookId") @NotBlank @NotNull @Positive(message = "bookId must be a positive number") Long bookId,
            @PathVariable("patronId") @NotBlank @NotNull @Positive(message = "patronId must be a positive number") Long patronId) {
        Borrowing borrowing = borrowingService.returnBook(bookId, patronId);
        return ResponseEntity.ok(borrowing);
    }


}