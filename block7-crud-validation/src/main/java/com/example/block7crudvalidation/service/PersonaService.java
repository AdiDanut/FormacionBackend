package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.controller.dto.PersonaDTO;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface PersonaService {
    PersonaDTO addPersona(PersonaDTO personaDTO) throws UnprocessableEntityException;

    Page<PersonaDTO> getAllPersonasWithPagination(String username, String name, String surname, Date fechaCreacion, int pageNumber, int size);

    PersonaDTO getPersonaById(Long id) throws ClassNotFoundException;

    PersonaDTO getPersonaByUsuario(String usuario) throws ClassNotFoundException;

}

