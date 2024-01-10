package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @NotNull
    @PositiveOrZero
    private Long id;
    @NotBlank(message = "Title is required")
    @NotNull
    private String title;
    @NotBlank(message = "Author is required")
    @NotNull
    private String author;
    @NotBlank
    @NotNull
    @Positive(message = "Publication year must be a positive number")
    @Min(value = 1970, message = "Publication year should not be less than 1970")
    @Max(value = 2024, message = "Publication year should not be greater than 2024")
    private Long publicationYear;
    @NotBlank
    @Pattern(regexp = "^\\d{13}$", message = "Invalid ISBN format. Must be a 13-digit number.")
    private String isbn;

    @OneToMany(mappedBy = "book")
    private List<Borrowing> borrowingRecords;

}
