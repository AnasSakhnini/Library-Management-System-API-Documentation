package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exception.CustomException;
import com.example.librarymanagementsystem.projection.PatronProjection;
import com.example.librarymanagementsystem.repository.PatronRepository;
import com.example.librarymanagementsystem.validation.BookPartialUpdateRequest;
import com.example.librarymanagementsystem.validation.PatronPartialUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<PatronProjection> getAllPatrons() {
        return patronRepository.getAllBy();
    }

    public PatronProjection getPatronById(Long id) {
        Optional<PatronProjection> patronOptional = patronRepository.getPatronById(id);
        if(patronOptional.isEmpty()){
            throw new CustomException("Patron not found with ID: " + id);
        }
        return patronOptional.get();
    }

    public Patron addPatron(Patron patron) {
        // Add business logic or validation if needed
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron updatedPatron) {
        // Add business logic or validation if needed
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patron = optionalPatron.get();
            patron.setName(updatedPatron.getName());
            patron.setContactInformation(updatedPatron.getContactInformation());
            patron = patronRepository.save(patron);
            return patron;
        } else {
            throw new CustomException("Patron not found with ID: " + id);
        }
    }

    public void updatePatronPartially(Long id, PatronPartialUpdateRequest updatedPatron) {
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patron = optionalPatron.get();
            // Update Patron's attributes based on the update request
            if (updatedPatron.getName() != null) {
                patron.setName(updatedPatron.getName());
            }
            if (updatedPatron.getContactInformation() != null) {
                patron.setContactInformation(updatedPatron.getContactInformation());
            }
            patronRepository.save(patron);
        } else {
            throw new CustomException("Patron not found with ID: " + id);
        }
    }


    public void deletePatron(Long id) {
        // Add business logic or validation if needed
        Optional<Patron> optionalPatron = patronRepository.findById(id);
        if (optionalPatron.isPresent()) {
            Patron patron = optionalPatron.get();
            patronRepository.delete(patron);
        } else {
            throw new CustomException("Patron not found with ID: " + id);
        }
    }
}
