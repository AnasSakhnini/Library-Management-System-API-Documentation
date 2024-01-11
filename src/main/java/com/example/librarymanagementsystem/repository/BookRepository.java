package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.projection.BookProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<BookProjection> getAllBy();
    Optional<BookProjection> getBookById(Long id);

}