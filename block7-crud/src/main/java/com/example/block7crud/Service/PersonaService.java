package com.example.block7crud.Service;

import com.example.block7crud.Entity.PersonaEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface PersonaService {

    PersonaEntity addPersona(PersonaEntity persona);
    List<PersonaEntity> getAllPersonas();
    PersonaEntity updatePersona(Long id, PersonaEntity personaDetails) throws ClassNotFoundException;
    void deletePersona(Long id, Long id2) throws ClassNotFoundException;
    PersonaEntity getPersonaById(Long id) throws ClassNotFoundException;
    List<PersonaEntity> getPersonasByNombre(String nombre);


}

