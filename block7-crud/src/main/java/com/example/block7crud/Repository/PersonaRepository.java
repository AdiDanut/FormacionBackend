package com.example.block7crud.Repository;

import com.example.block7crud.Entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    List<PersonaEntity> findByNombre(String nombre);
}
