package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.Borrowing;
import com.example.librarymanagementsystem.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
   Optional<Borrowing> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);
   List<Borrowing> findByBookAndReturnDateIsNull(Book book);
   List<Borrowing> findByPatronAndReturnDateIsNull(Patron patron);

   Borrowing findByBookIdAndPatronIdAndReturnDateIsNull(Long bookId, Long patronId);
}