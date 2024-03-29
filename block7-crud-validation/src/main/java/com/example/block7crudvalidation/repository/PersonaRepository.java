package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long>, JpaSpecificationExecutor<PersonaEntity> {

    @Query("SELECT P FROM PersonaEntity P " +
            "WHERE P.usuario = :usuario")
    Optional<PersonaEntity> findByUsuario(String usuario);
}
