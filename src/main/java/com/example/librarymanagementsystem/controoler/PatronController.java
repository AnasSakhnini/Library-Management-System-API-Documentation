package com.example.librarymanagementsystem.controoler;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.service.PatronService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patrons")
@Validated
public class PatronController {
    private final PatronService patronService;

    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id) {
        Patron patron = patronService.getPatronById(id);

        return ResponseEntity.ok(patron);
    }

    @PostMapping
    public Patron addPatron(@RequestBody @Valid Patron patron) {
        return patronService.addPatron(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id,
            @RequestBody @Valid  Patron updatedPatron) {
        return ResponseEntity.ok(patronService.updatePatron(id,updatedPatron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id) {
        patronService.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}