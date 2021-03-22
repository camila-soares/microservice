package com.microservice.authentication.services;

import com.microservice.authentication.dtos.UserDTO;
import com.microservice.authentication.entity.User;
import com.microservice.authentication.mapper.UserMapper;
import com.microservice.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        var user  = userRepository.findByUsername( username );
        if( user != null ){
            return user;
        }else {
            throw new UsernameNotFoundException( "Usuário" + username + " não encontrado!" );
        }
    }

    public User saveUser( UserDTO userDTO ) {
        User user;
        user = userRepository.save( UserMapper.toUser( userDTO ) );
        return user;
    }
}
