package com.microservice.commons.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;


    @NotBlank(message = "Missing fields")
    private String firstName;

    @NotBlank(message = "Missing fields")
    private String lastName;

    @NotBlank(message = "Missing fields")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Missing fields")
    @NotNull(message = "Missing fields")
    private String password;
}
