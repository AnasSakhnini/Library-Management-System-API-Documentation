package com.example.librarymanagementsystem.projection;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public interface PatronProjection {
    Long getId();
    String getName();
    String getContactInformation();
}
