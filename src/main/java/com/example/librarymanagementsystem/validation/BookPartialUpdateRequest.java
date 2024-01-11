package com.example.librarymanagementsystem.validation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BookPartialUpdateRequest {

    @PositiveOrZero
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotNull(message = "Book's publication year must not be null")
    @Positive(message = "Publication year must be a positive number")
    @Min(value = 1970, message = "Publication year should not be less than 1970")
    @Max(value = 2024, message = "Publication year should not be greater than 2024")
    private Long publicationYear;
    @NotBlank(message = "ISBN must not be blank")
    @Pattern(regexp = "^\\d{13}$", message = "Invalid ISBN format. Must be a 13-digit number.")
    private String isbn;
}
