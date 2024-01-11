package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @Valid
    @NotNull
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    @Valid
    @NotNull
    private Patron patron;

    @PastOrPresent( message = "Borrowed date should be valid")
    @NotNull
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Local date should be yyyy-mm-dd")
    private LocalDate borrowedDate;

    @PastOrPresent( message = "Return date should be valid")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Return date should be yyyy-mm-dd")
    private LocalDate returnDate;

}