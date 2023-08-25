package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {

    @Query("SELECT S FROM StudentEntity S " +
            "WHERE S.persona.idPersona = :idPersona")
    Optional<StudentEntity> findByIdPersona(@Param("idPersona") Long idPersona);
}
