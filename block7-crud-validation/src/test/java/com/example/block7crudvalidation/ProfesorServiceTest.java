package com.example.block7crudvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.example.block7crudvalidation.entity.StudentEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.block7crudvalidation.controller.dto.ProfesorDTO;
import com.example.block7crudvalidation.entity.PersonaEntity;
import com.example.block7crudvalidation.entity.ProfesorEntity;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import com.example.block7crudvalidation.service.impl.ProfesorServiceImpl;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class ProfesorServiceTest {

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProfesorServiceImpl profesorService;

    @Test
    public void testCreateProfesor() {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdPersona(1L);

        PersonaEntity persona = new PersonaEntity();

        ProfesorEntity profesorEntity = new ProfesorEntity();

        when(modelMapper.map(profesorDTO, ProfesorEntity.class)).thenReturn(profesorEntity);
        when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));
        when(profesorRepository.save(profesorEntity)).thenReturn(profesorEntity);
        when(modelMapper.map(profesorEntity, ProfesorDTO.class)).thenReturn(profesorDTO);

        ProfesorDTO result = profesorService.createProfesor(profesorDTO);

        assertEquals(profesorDTO, result);
    }

    @Test
    public void testCreateProfesor_studentExists_throwsException() {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdPersona(1L);

        when(studentRepository.findByIdPersona(1L)).thenReturn(Optional.of(new StudentEntity()));

        assertThrows(UnprocessableEntityException.class, () -> {
            profesorService.createProfesor(profesorDTO);
        });
    }

    @Test
    public void testGetProfesor() {
        ProfesorEntity profesor = new ProfesorEntity();
        profesor.setIdProfesor("1");

        when(profesorRepository.findById("1")).thenReturn(Optional.of(profesor));

        ProfesorDTO expected = new ProfesorDTO();
        when(modelMapper.map(profesor, ProfesorDTO.class)).thenReturn(expected);

        ProfesorDTO result = profesorService.getProfesor("1");

        assertEquals(expected, result);
    }

    @Test
    public void testGetAllProfesores() {
        ProfesorEntity prof1 = new ProfesorEntity();
        ProfesorDTO profDto1 = new ProfesorDTO();

        ProfesorEntity prof2 = new ProfesorEntity();
        ProfesorDTO profDto2 = new ProfesorDTO();

        when(profesorRepository.findAll()).thenReturn(List.of(prof1, prof2));
        when(modelMapper.map(prof1, ProfesorDTO.class)).thenReturn(profDto1);
        when(modelMapper.map(prof2, ProfesorDTO.class)).thenReturn(profDto2);

        List<ProfesorDTO> expected = List.of(profDto1, profDto2);

        assertEquals(expected, profesorService.getAllProfesores());
    }

    @Test
    public void testUpdateProfesor() {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdPersona(1L);

        ProfesorEntity profesorEntity = new ProfesorEntity();
        when(modelMapper.map(profesorDTO, ProfesorEntity.class)).thenReturn(profesorEntity);

        when(profesorRepository.existsById("1")).thenReturn(true);
        when(personaRepository.findById(1L)).thenReturn(Optional.of(new PersonaEntity()));
        when(profesorRepository.save(profesorEntity)).thenReturn(profesorEntity);
        when(modelMapper.map(profesorEntity, ProfesorDTO.class)).thenReturn(profesorDTO);

        ProfesorDTO result = profesorService.updateProfesor("1", profesorDTO);

        assertEquals(profesorDTO, result);
    }

    @Test
    public void testDeleteProfesor() {
        profesorService.deleteProfesor("1");

        verify(profesorRepository).deleteById("1");
    }

}