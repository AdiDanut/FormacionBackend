package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity, String> {

    @Query("SELECT P FROM ProfesorEntity P " +
            "WHERE P.persona.idPersona = :idPersona")
    Optional<ProfesorEntity> findByIdPersona(@Param("idPersona") Long idPersona);
}
