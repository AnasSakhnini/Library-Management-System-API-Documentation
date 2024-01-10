package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Contact information is required")
    private String contactInformation;

    @OneToMany(mappedBy = "patron")
    private List<Borrowing> borrowingRecords;

}