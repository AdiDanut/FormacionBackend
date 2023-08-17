package com.example.block7crudvalidation.Service;

import com.example.block7crudvalidation.Controller.DTO.PersonaDTO;

import java.util.List;

public interface PersonaService {
    PersonaDTO addPersona(PersonaDTO personaDTO) throws Exception;

    List<PersonaDTO> getAllPersonas();

    PersonaDTO getPersonaById(Long id) throws Exception;

    PersonaDTO getPersonaByUsuario(String usuario) throws Exception;

}

