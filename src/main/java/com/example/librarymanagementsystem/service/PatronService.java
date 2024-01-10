package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElse(null);
    }

    public Patron addPatron(Patron patron) {
        // Add business logic or validation if needed
        return patronRepository.save(patron);
    }

    public Patron updatePatron(Long id, Patron updatedPatron) {
        // Add business logic or validation if needed
        Patron existingPatron = patronRepository.findById(id).orElse(null);
        if (existingPatron != null) {
            existingPatron.setName(updatedPatron.getName());
            existingPatron.setContactInformation(updatedPatron.getContactInformation());
            // Update other fields as needed
            return patronRepository.save(existingPatron);
        }
        return null; // or throw an exception
    }

    public void deletePatron(Long id) {
        // Add business logic or validation if needed
        patronRepository.deleteById(id);
    }
}
