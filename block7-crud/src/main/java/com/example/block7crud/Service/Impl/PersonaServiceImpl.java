package com.example.block7crud.Service.Impl;

import com.example.block7crud.Entity.PersonaEntity;
import com.example.block7crud.Repository.PersonaRepository;
import com.example.block7crud.Service.PersonaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public PersonaEntity addPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity updatePersona(Long id, PersonaEntity personaDetails) {
        try {
            Optional<PersonaEntity> persona = personaRepository.findById(id);

            if (persona.isEmpty()) {
                throw new ClassNotFoundException();
            }

            PersonaEntity personaEntity = persona.get();

            if (personaDetails.getNombre() != null) {
                personaEntity.setNombre(personaDetails.getNombre());
            }
            if (personaDetails.getPoblacion() != null) {
                personaEntity.setPoblacion(personaDetails.getPoblacion());
            }

            return personaRepository.save(personaEntity);
        } catch (Exception ex) {

            throw new RuntimeException("Error al actualizar la persona.", ex);
        }
    }

    @Override
    @Transactional
    public void deletePersona(Long id1, Long id2) {
            Optional<PersonaEntity> persona = personaRepository.findById(id1);
            personaRepository.delete(persona.get());
            deletePersona2(id2);

    }

    private void deletePersona2(Long id2) {
        Optional<PersonaEntity> persona2 = personaRepository.findById(id2);
        personaRepository.delete(persona2.get());
    }

    @Override
    public PersonaEntity getPersonaById(Long id) {
        try {
            Optional<PersonaEntity> persona = personaRepository.findById(id);

            if (persona.isEmpty()) {
                throw new ClassNotFoundException();
            }

            return persona.get();
        } catch (Exception ex) {

            throw new RuntimeException("Error al obtener la persona.", ex);
        }
    }

    @Override
    public List<PersonaEntity> getPersonasByNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    @Override
    public List<PersonaEntity> getAllPersonas() {
        return personaRepository.findAll();
    }
}
