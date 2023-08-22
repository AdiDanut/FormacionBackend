package com.example.block11uploaddownloadfiles.Repository;

import com.example.block11uploaddownloadfiles.Entity.FicheroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FicheroRepository extends JpaRepository<FicheroEntity, Long> {


    Optional<FicheroEntity> findByName(@Param("name") String name);
}
