package com.microservice.authentication.repositories;

import com.microservice.authentication.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository< Permission, Long > {

    Permission findByDescription(String description);

    //@Query("SELECT p FROM Permission p WHERE p.description =: description")
    //Permission findByDescription( @Param ( "description" ) String description);
}
