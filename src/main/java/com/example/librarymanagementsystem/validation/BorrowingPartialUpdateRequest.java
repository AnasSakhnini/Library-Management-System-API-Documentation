package com.example.librarymanagementsystem.validation;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Patron;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowingPartialUpdateRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotBlank
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;
    private LocalDate borrowedDate;
    private LocalDate returnDate;

}
