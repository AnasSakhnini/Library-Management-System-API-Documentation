package com.example.librarymanagementsystem.dto;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Patron;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;
@Data
public class BorrowingDto {
    private Long id;

    private Long bookId;

    private Long patronId;

    private LocalDate borrowedDate;

    private LocalDate returnDate;
}
