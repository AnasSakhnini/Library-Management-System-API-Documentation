package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.projection.BookProjection;
import com.example.librarymanagementsystem.projection.PatronProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Long> {
    List<PatronProjection> getAllBy();
    Optional<PatronProjection> getPatronById(Long id);
}
