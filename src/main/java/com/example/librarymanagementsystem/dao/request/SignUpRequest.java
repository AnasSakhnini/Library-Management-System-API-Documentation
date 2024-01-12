package com.example.librarymanagementsystem.dao.request;

import com.example.librarymanagementsystem.annotation.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "firstName should not be blank")
    private String firstName;
    @NotBlank(message = "lastName should not be blank")
    private String lastName;
    @NotBlank(message = "email should not be blank")
    @Email(message = "email should be valid")
    private String email;
    @Password
    private String password;
}
