package com.example.block13mongodb.Services;

import com.example.block13mongodb.Controller.DTO.PersonaDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonaService {
    PersonaDTO createPersona(PersonaDTO personaDTO);
    PersonaDTO getPersonaById(String id);
    Page<PersonaDTO> getAllPersonas(int page, int size);
    PersonaDTO updatePersona(String id, PersonaDTO personaDTO);
    void deletePersona(String id);
}

