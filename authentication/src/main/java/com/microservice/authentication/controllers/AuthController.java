package com.microservice.authentication.controllers;

import com.microservice.authentication.dtos.UserDTO;
import com.microservice.authentication.entity.User;
import com.microservice.authentication.jwt.JwtTokenProvider;
import com.microservice.authentication.mapper.UserMapper;
import com.microservice.authentication.repositories.UserRepository;
import com.microservice.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO ) {
        try {
            var username = userDTO.getUsername();
            var password = userDTO.getPassword();

            manager.authenticate( new UsernamePasswordAuthenticationToken( username, password ) );

            var user = userRepository.findByUsername( username );
            var token = "";

            if( user != null ) {
                token = provider.createToken( username, user.getRoles() );
            } else {
                throw new UsernameNotFoundException( "Usuário nao encontrado" );
            }
            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);
            return ResponseEntity.ok(model);

        }catch( BadCredentialsException e ) {
            throw new BadCredentialsException( "Usuário ou senha inválidos" );
        }
    }

    @PostMapping("/user")
    @ResponseStatus( HttpStatus.CREATED )
    public ResponseEntity< UserDTO > createUser( @Validated @RequestBody UserDTO userDTO ) throws Exception {
        final User user = service.saveUser( userDTO );
        System.out.println(String.format("User saved: %s", user.toString()));
        return new ResponseEntity( UserMapper.toUserDTO( user ), HttpStatus.CREATED );

    }
}
