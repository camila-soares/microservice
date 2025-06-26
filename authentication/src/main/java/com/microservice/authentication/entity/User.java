package com.microservice.authentication.entity;

import com.microservice.commons.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

//    @Column(name = "accountNonExpired")
//    private Boolean accountNonExpired;
//
//    @Column(name = "accountNonLocked")
//    private Boolean accountNonLocked;
//
//    @Column(name = "credentialsNonExpired")
//    private Boolean credentialsNonExpired;
//
//    @Column(name = "enable")
//    private Boolean enable;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id_user")},
//            inverseJoinColumns = {@JoinColumn(name = "id_permissions")})
//    private List<Permission> permissions;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.permissions;
//    }
//
//    public List<String> getRoles() {
//        List<String> roles = new ArrayList<>();
//        this.permissions.stream()
//                .forEach(p -> {
//                    roles.add(p.getDescription());
//                });
//        return roles;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enable;
//    }
}
