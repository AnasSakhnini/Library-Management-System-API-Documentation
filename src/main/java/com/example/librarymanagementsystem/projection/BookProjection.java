package com.example.librarymanagementsystem.projection;

import com.example.librarymanagementsystem.entity.Borrowing;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.*;

import java.util.List;

public interface BookProjection {
    Long getId();
    String getTitle();
    String getAuthor();
    Long getPublicationYear();
    String getIsbn();

}
