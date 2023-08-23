package com.example.block7crudvalidation.Service.Impl;

import com.example.block7crudvalidation.Controller.DTO.PersonaDTO;
import com.example.block7crudvalidation.Entity.PersonaEntity;
import com.example.block7crudvalidation.Exceptions.CustomError;
import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Factory.CustomErrorFactory;
import com.example.block7crudvalidation.Repository.PersonaRepository;
import com.example.block7crudvalidation.Service.PersonaService;
import com.example.block7crudvalidation.Specifications.PersonaSpecs;
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
import java.util.stream.Collectors;

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
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener la persona.", ex);
        }
    }

    @Override
    public PersonaDTO getPersonaByUsuario(String usuario) throws EntityNotFoundException {
        try {
            PersonaEntity persona = personaRepository.findByUsuario(usuario)
                    .orElseThrow(() -> new EntityNotFoundException(CustomErrorFactory.createNotFoundByUsuario(usuario)));

            return convertToDTO(persona);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener la persona por usuario.", ex);
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
                .collect(Collectors.toList());

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
