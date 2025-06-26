package com.microservice.authentication.repositories;

import com.microservice.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    //Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

//    Optional<User> findByUsername( String email );

    //@Query("SELECT u FROM User u WHERE u.username =: username")
    //User findByUsername( @Param( "username" ) String username);
}
