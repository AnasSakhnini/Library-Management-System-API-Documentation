package com.example.librarymanagementsystem.unit.service;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.projection.BookProjection;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookServiceTest {
    @Autowired
    BookService bookService;
    @Autowired
    ModelMapper modelMapper;
    @MockBean
    BookRepository bookRepository;

    @Test
    void getBookById__notValidId(){
        Optional<BookProjection> book = Optional.empty();
        Mockito.when(bookRepository.getBookById(Mockito.anyLong())).thenReturn(book);
        try{
            bookService.getBookById(-1L);
        }
        catch (Exception ex){
            assertEquals(ex.getMessage(), "Book not found with ID: -1");
        }
    }




}
