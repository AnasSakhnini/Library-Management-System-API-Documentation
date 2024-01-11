package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.projection.PatronProjection;
import com.example.librarymanagementsystem.service.PatronService;
import com.example.librarymanagementsystem.validation.PatronPartialUpdateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@Validated
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<PatronProjection> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatronProjection> getPatronById(
            @PathVariable("id") @PositiveOrZero(message = "ID must be a positive number")  @NotNull Long id) {
        PatronProjection patron = patronService.getPatronById(id);

        return ResponseEntity.ok(patron);
    }

    @PostMapping
    public ResponseEntity<Object> addPatron(@RequestBody @Valid Patron patron) {
        patronService.addPatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(
            @PathVariable("id") @PositiveOrZero(message = "ID must be a positive number")  @NotNull Long id,
            @RequestBody @Valid  Patron updatedPatron) {
        patronService.updatePatron(id,updatedPatron);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateBookPartially(
            @PathVariable("id")  @PositiveOrZero(message = "ID is less than 0, it should be a positive number") long id,
            @RequestBody @Valid PatronPartialUpdateRequest updatedPatron) {
        patronService.updatePatronPartially(id, updatedPatron);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(
            @PathVariable("id") @PositiveOrZero(message = "ID must be a positive number") @NotNull Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}