package com.microservice.commons.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Missing fields")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String password;
}
