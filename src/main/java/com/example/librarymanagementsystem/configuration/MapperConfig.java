package com.example.librarymanagementsystem.configuration;

import com.example.librarymanagementsystem.dto.BorrowingDto;
import com.example.librarymanagementsystem.entity.Borrowing;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper getMapperRequest(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap( Borrowing.class, BorrowingDto.class);
        return modelMapper;
    }
    @Bean
    ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
