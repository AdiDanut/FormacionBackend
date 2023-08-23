package com.example.block7crudvalidation.Service;

import com.example.block7crudvalidation.Controller.DTO.PersonaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PersonaService {
    PersonaDTO addPersona(PersonaDTO personaDTO) throws Exception;

    Page<PersonaDTO> getAllPersonasWithPagination(String username, String name, String surname, Date fechaCreacion, int pageNumber, int size);

    PersonaDTO getPersonaById(Long id) throws Exception;

    PersonaDTO getPersonaByUsuario(String usuario) throws Exception;

}

