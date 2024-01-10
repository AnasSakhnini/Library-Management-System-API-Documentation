package com.example.librarymanagementsystem.controoler;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exception.CustomException;
import com.example.librarymanagementsystem.repository.PatronRepository;
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
    private final PatronRepository patronRepository;

    public PatronController(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatronById(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if(optionalPatron.isEmpty()){
                throw new CustomException("Patron not found with ID: " + id);
        }
        return ResponseEntity.ok(optionalPatron.get());
    }

    @PostMapping
    public Patron addPatron(@RequestBody @Valid Patron patron) {
        return patronRepository.save(patron);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id,
            @RequestBody @Valid  Patron updatedPatron) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patron = optionalPatron.get();
            patron.setName(updatedPatron.getName());
            patron.setContactInformation(updatedPatron.getContactInformation());
            patronRepository.save(patron);
            return ResponseEntity.ok(patron);
        } else {
            throw new CustomException("Patron not found with ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(
            @PathVariable("id") @Positive(message = "ID must be a positive number") @NotBlank @NotNull Long id) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patron = optionalPatron.get();
            patronRepository.delete(patron);
            return ResponseEntity.noContent().build();
        } else {
            throw new CustomException("Patron not found with ID: " + id);
        }
    }
}