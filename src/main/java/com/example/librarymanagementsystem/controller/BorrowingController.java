package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.dto.BorrowingDto;
import com.example.librarymanagementsystem.service.BorrowingService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrow")
@Validated
public class BorrowingController {
    final BorrowingService borrowingService;


    public BorrowingController(BorrowingService borrowingService, ModelMapper modelMapper) {
        this.borrowingService = borrowingService;
    }

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<Object> borrowBook(
            @PathVariable("bookId")    @NotNull @PositiveOrZero(message = "Book Id must be a positive number")  Long bookId,
            @PathVariable("patronId")  @NotNull @PositiveOrZero(message = "Patron Id must be a positive number") Long patronId) {
        BorrowingDto borrowing = borrowingService.borrowBook(bookId, patronId);
        return ResponseEntity.ok(borrowing);
    }

    @PostMapping("/{bookId}/patron/{patronId}/return")
    public ResponseEntity<Object> returnBook(
            @PathVariable("bookId")   @NotNull @PositiveOrZero(message = "Book Id must be a positive number") Long bookId,
            @PathVariable("patronId") @NotNull @PositiveOrZero(message = "Patron Id must be a positive number") Long patronId) {
        BorrowingDto borrowing = borrowingService.returnBook(bookId, patronId);
        return ResponseEntity.ok(borrowing);
    }


}