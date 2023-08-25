package com.example.block13mongodb.Services.Impl;

import com.example.block13mongodb.Controller.DTO.PersonaDTO;
import com.example.block13mongodb.Entity.PersonaEntity;
import com.example.block13mongodb.Repository.PersonaRepository;
import com.example.block13mongodb.Services.PersonaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private  PersonaRepository personaRepository;

    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public PersonaDTO createPersona(PersonaDTO personaDTO) {
        PersonaEntity personaEntity = modelMapper.map(personaDTO, PersonaEntity.class);
        personaEntity = personaRepository.save(personaEntity);
        return modelMapper.map(personaEntity, PersonaDTO.class);
    }

    @Override
    public PersonaDTO getPersonaById(String id) {
        Optional<PersonaEntity> personaEntity = personaRepository.findById(id);
        return personaEntity.map(entity -> modelMapper.map(entity, PersonaDTO.class)).orElse(null);
    }

    @Override
    public Page<PersonaDTO> getAllPersonas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PersonaEntity> personas = personaRepository.findAll(pageable);
        List<PersonaDTO> personasDTOList = personas.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(personasDTOList, pageable, personas.getTotalElements());
    }

    @Override
    public PersonaDTO updatePersona(String id, PersonaDTO personaDTO) {
        Optional<PersonaEntity> optionalPersonaEntity = personaRepository.findById(id);
        if (optionalPersonaEntity.isPresent()) {
            PersonaEntity personaEntity = optionalPersonaEntity.get();
            personaEntity = personaRepository.save(personaEntity);
            return modelMapper.map(personaEntity, PersonaDTO.class);
        }
        return null;
    }

    @Override
    public void deletePersona(String id) {
        personaRepository.deleteById(id);
    }

    private PersonaDTO convertToDTO(PersonaEntity persona) {
        return modelMapper.map(persona, PersonaDTO.class);
    }
}
