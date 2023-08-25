package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.entity.AsignaturaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<AsignaturaEntity, String> {

    @Query("SELECT a FROM AsignaturaEntity a " +
            "JOIN a.student s WHERE s.idStudent = :idStudent")
    List<AsignaturaEntity> findAsignaturasByStudentId(@Param("idStudent") String idStudent);

}
