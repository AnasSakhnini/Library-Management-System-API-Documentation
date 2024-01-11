package com.example.librarymanagementsystem.configuration;

import com.example.librarymanagementsystem.dto.BorrowingDto;
import com.example.librarymanagementsystem.entity.Borrowing;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean(name="Request.ValidateClaim.Mapper")
    ModelMapper getMapperRequest(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap( Borrowing.class, BorrowingDto.class);
//        .addMappings(mapper -> {
//            mapper.map(Borrowing::getBook::getId,
//                    BorrowingDto::setBookId());
//
//        });

        return modelMapper;
    }
}
