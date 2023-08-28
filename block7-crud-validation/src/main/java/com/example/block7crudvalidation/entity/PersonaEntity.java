package com.example.block7crudvalidation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PERSONA")
public class PersonaEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(nullable = false, length = 10)
    private String usuario;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String surname;

    @Column(nullable = false)
    private String companyEmail;

    @Column(nullable = false)
    private String personalEmail;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean active;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date createdDate;

    private String imageUrl;

    private Date terminationDate;

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

