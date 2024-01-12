package com.example.librarymanagementsystem.service.security;


import com.example.librarymanagementsystem.dao.request.SignUpRequest;
import com.example.librarymanagementsystem.dao.request.SigninRequest;
import com.example.librarymanagementsystem.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
