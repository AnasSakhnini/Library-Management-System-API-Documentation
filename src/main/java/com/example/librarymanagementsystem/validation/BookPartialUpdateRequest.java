package com.example.librarymanagementsystem.validation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookPartialUpdateRequest {

    private String title;
    private String author;
    @Positive(message = "Publication year must be a positive number")
    @Min(value = 1970, message = "Publication year should not be less than 1970")
    @Max(value = 2024, message = "Publication year should not be greater than 2024")
    private Long publicationYear;
    @Pattern(regexp = "^\\d{13}$", message = "Invalid ISBN format. Must be a 13-digit number.")
    private String isbn;
}
