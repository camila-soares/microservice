package com.microservice.authentication.controllers;

import com.microservice.authentication.entity.User;

import com.microservice.authentication.security.JwtTokenProvider;
import com.microservice.authentication.services.UserService;
import com.microservice.commons.dtos.LoginDTO;
import com.microservice.commons.dtos.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            var user = service.authenticate(loginDTO);
            var token = "";

            if (user != null) {
              token =  jwtTokenProvider.createToken(user);
              jwtTokenProvider.validateToken(token);
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("Authorization", "Bearer " + token);

            return ResponseEntity.ok(model);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO dto) throws Exception {
        User user = service.saveUser(dto);
         UserDTO userDTO = UserDTO.builder().id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CLIENTE', 'ADMIN')")
    public ResponseEntity<Void> updateUser(
            @PathVariable Long id,
                                              @Valid @RequestBody UserDTO dto) throws Exception {
        service.updateUser(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
