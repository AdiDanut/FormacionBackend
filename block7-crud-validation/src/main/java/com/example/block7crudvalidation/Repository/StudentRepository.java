package com.example.block7crudvalidation.Repository;

import com.example.block7crudvalidation.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
}
