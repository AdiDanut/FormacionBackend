package com.example.block7crudvalidation.Service.Impl;

import com.example.block7crudvalidation.Controller.DTO.ProfesorDTO;
import com.example.block7crudvalidation.Entity.PersonaEntity;
import com.example.block7crudvalidation.Entity.ProfesorEntity;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Factory.CustomErrorFactory;
import com.example.block7crudvalidation.Repository.PersonaRepository;
import com.example.block7crudvalidation.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Repository.StudentRepository;
import com.example.block7crudvalidation.Service.ProfesorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ProfesorDTO createProfesor(ProfesorDTO profesorDTO) {
        if (studentRepository.findByIdPersona(profesorDTO.getIdPersona()).isPresent()){
            throw new UnprocessableEntityException(CustomErrorFactory.createUserProfesor());
        }

        ProfesorEntity profesorEntity = modelMapper.map(profesorDTO, ProfesorEntity.class);
        Optional<PersonaEntity> persona = personaRepository.findById(profesorDTO.getIdPersona());

        if (persona.isPresent()){
            profesorEntity.setPersona(persona.get());
        }
        profesorEntity = profesorRepository.save(profesorEntity);
        return modelMapper.map(profesorEntity, ProfesorDTO.class);
    }

    @Override
    public ProfesorDTO getProfesor(String id) {
        ProfesorEntity profesorEntity = profesorRepository.findById(id).orElse(null);
        return modelMapper.map(profesorEntity, ProfesorDTO.class);
    }

    @Override
    public List<ProfesorDTO> getAllProfesores() {
        List<ProfesorEntity> profesorEntities = profesorRepository.findAll();
        return profesorEntities.stream()
                .map(entity -> modelMapper.map(entity, ProfesorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProfesorDTO updateProfesor(String id, ProfesorDTO profesorDTO) {
        if (profesorRepository.existsById(id)) {
            ProfesorEntity profesorEntity = modelMapper.map(profesorDTO, ProfesorEntity.class);
            profesorEntity.setIdProfesor(id);

            Optional<PersonaEntity> persona = personaRepository.findById(profesorDTO.getIdPersona());
            if (persona.isPresent()){
                profesorEntity.setPersona(persona.get());
            }

            profesorEntity = profesorRepository.save(profesorEntity);
            return modelMapper.map(profesorEntity, ProfesorDTO.class);
        }
        return null;
    }

    @Override
    public void deleteProfesor(String id) {
        profesorRepository.deleteById(id);
    }
}
