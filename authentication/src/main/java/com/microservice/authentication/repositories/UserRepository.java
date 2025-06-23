package com.microservice.authentication.repositories;

import com.microservice.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    //Optional<User> findByUsername(String username);

    User findByUsername(String email);

    //Optional<User> findByUsernames( String username );

    //@Query("SELECT u FROM User u WHERE u.username =: username")
    //User findByUsername( @Param( "username" ) String username);
}
