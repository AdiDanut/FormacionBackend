package com.example.block7crudvalidation.Repository;

import com.example.block7crudvalidation.Entity.ProfesorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfesorRepository extends JpaRepository<ProfesorEntity, String> {

}
