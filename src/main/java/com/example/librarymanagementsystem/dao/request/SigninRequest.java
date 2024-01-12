package com.example.librarymanagementsystem.dao.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SigninRequest {
    @NotBlank(message = "email should not be blank")
    private String email;
    @NotBlank(message = "password should not be blank")
    private String password;
}
