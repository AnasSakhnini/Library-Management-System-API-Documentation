package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotBlank
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    @NotBlank
    private Patron patron;
    @NotNull(message = "Borrow date is required")
    private LocalDate borrowedDate;
    private LocalDate returnDate;

}