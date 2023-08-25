package com.example.block13mongodb.Repository;

import com.example.block13mongodb.Entity.PersonaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonaRepository extends MongoRepository<PersonaEntity, String> {
}
