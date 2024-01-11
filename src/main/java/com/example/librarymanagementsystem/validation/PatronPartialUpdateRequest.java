package com.example.librarymanagementsystem.validation;

import com.example.librarymanagementsystem.entity.Borrowing;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
@Data
public class PatronPartialUpdateRequest {
    private String name;
    private String contactInformation;

}
