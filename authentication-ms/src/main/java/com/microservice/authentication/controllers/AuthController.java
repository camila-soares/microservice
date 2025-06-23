package com.microservice.authentication.controllers;

import com.microservice.authentication.dtos.UserDTO;
import com.microservice.authentication.entity.User;

import com.microservice.authentication.mapper.UserMapper;
import com.microservice.authentication.repositories.UserRepository;
import com.microservice.authentication.security.JwtTokenProvider;
import com.microservice.authentication.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/login")
public class AuthController {


    private final UserMapper userMapper;

    private final JwtTokenProvider provider;

    private final UserService service;

    private final UserRepository userRepository;

    public AuthController(UserMapper userMapper, UserDetailsService userDetailsService, JwtTokenProvider provider, UserService service, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.provider = provider;
        this.service = service;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {

            var user =  service.authenticate(userDTO);
            var token = "";
            if( user != null ) {
                token = provider.createToken( user.getUsername(), user.getRoles() );
                provider.validateToken(token);
            } else {
                throw new UsernameNotFoundException("Usuário nao encontrado");
            }
            Map<Object, Object> model = new HashMap<>();
            model.put("Authorization", "Bearer " + token);

            return ResponseEntity.ok(model);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@Validated @RequestBody UserDTO userDTO) throws Exception {
        final User user = service.saveUser(userDTO);
        System.out.printf("User saved: %s%n", user.toString());
        return new ResponseEntity<>(userMapper.toUserDTO(user), HttpStatus.CREATED);

    }
}
