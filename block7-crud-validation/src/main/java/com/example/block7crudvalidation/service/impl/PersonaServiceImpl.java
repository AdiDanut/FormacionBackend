package com.example.block7crudvalidation.service.impl;

import com.example.block7crudvalidation.controller.dto.PersonaDTO;
import com.example.block7crudvalidation.entity.PersonaEntity;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.exceptions.factory.CustomErrorFactory;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.service.PersonaService;
import com.example.block7crudvalidation.repository.specifications.PersonaSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonaDTO addPersona(PersonaDTO personaDTO) throws UnprocessableEntityException {
        validatePersonaDTO(personaDTO);

        PersonaEntity persona = convertToEntity(personaDTO);
        PersonaEntity savedPersona = personaRepository.save(persona);
        return convertToDTO(savedPersona);
    }

    @Override
    public PersonaDTO getPersonaById(Long id) throws EntityNotFoundException {
        try {
            PersonaEntity persona = personaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(CustomErrorFactory.createNotFound("Persona", id)));

            return convertToDTO(persona);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(CustomErrorFactory.createNotFound("Persona", id));
        }
    }

    @Override
    public PersonaDTO getPersonaByUsuario(String usuario) throws EntityNotFoundException {
        try {
            PersonaEntity persona = personaRepository.findByUsuario(usuario)
                    .orElseThrow(() -> new EntityNotFoundException(CustomErrorFactory.createNotFoundByUsuario(usuario)));

            return convertToDTO(persona);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException(CustomErrorFactory.createNotFoundByUsuario(usuario));
        }
    }

    @Override
    public Page<PersonaDTO> getAllPersonasWithPagination(String username, String name, String surname, Date fechaCreacion, int pageNumber, int size) {
        Pageable pageable = PageRequest.of(pageNumber, size);

        Specification<PersonaEntity> spec = PersonaSpecs.filter(username, name, surname, fechaCreacion);

        Page<PersonaEntity> personasPage = personaRepository.findAll(spec, pageable);
        List<PersonaDTO> personasDTOList = personasPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();

        return new PageImpl<>(personasDTOList, pageable, personasPage.getTotalElements());
    }

    private PersonaDTO convertToDTO(PersonaEntity persona) {
        return modelMapper.map(persona, PersonaDTO.class);
    }

    private PersonaEntity convertToEntity(PersonaDTO personaDTO) {
        return modelMapper.map(personaDTO, PersonaEntity.class);
    }

    private void validatePersonaDTO(PersonaDTO personaDTO) throws UnprocessableEntityException {
        if (personaDTO.getUsuario() == null) {
            throw new UnprocessableEntityException(CustomErrorFactory.createNullUser());
        }
        if (personaDTO.getUsuario().length() > 10) {
            throw new UnprocessableEntityException(CustomErrorFactory.createUserLength());
        }
    }
}
