package com.example.block7crudvalidation.Repository;

import com.example.block7crudvalidation.Entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByUsuario(String usuario);
}
