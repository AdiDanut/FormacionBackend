package com.example.block7crudvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.block7crudvalidation.exceptions.factory.CustomErrorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.block7crudvalidation.controller.dto.PersonaDTO;
import com.example.block7crudvalidation.entity.PersonaEntity;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.service.impl.PersonaServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PersonaServiceImpl personaService;

    @Mock
    private CustomErrorFactory customErrorFactory;


    @Test
    public void testAddPersona_success() throws Exception {
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setUsuario("testuser");

        PersonaEntity personaEntity = new PersonaEntity();

        when(modelMapper.map(personaDTO, PersonaEntity.class)).thenReturn(personaEntity);
        when(personaRepository.save(personaEntity)).thenReturn(personaEntity);
        when(modelMapper.map(personaEntity, PersonaDTO.class)).thenReturn(personaDTO);

        PersonaDTO result = personaService.addPersona(personaDTO);

        assertEquals(personaDTO, result);
    }

    @Test
    public void testAddPersona_nullUser_throwsException() {
        PersonaDTO personaDTO = new PersonaDTO();

        Exception exception = assertThrows(UnprocessableEntityException.class, () -> {
            personaService.addPersona(personaDTO);
        });

        String expectedMessage = String.valueOf(CustomErrorFactory.createNullUser());
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testGetPersonaById_success() throws Exception {
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setIdPersona(1L);

        when(personaRepository.findById(1L)).thenReturn(java.util.Optional.of(personaEntity));

        PersonaDTO expected = new PersonaDTO();
        when(modelMapper.map(personaEntity, PersonaDTO.class)).thenReturn(expected);

        PersonaDTO actual = personaService.getPersonaById(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetPersonaById_notFound() {
        when(personaRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            personaService.getPersonaById(1L);
        });

        String expectedMessage = String.valueOf(CustomErrorFactory.createNotFound("Persona", 1L));
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
    // Pruebas para getPersonaByUsuario

    @Test
    public void testGetPersonaByUsuario_success() throws Exception {

        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setUsuario("testuser");

        when(personaRepository.findByUsuario("testuser")).thenReturn(java.util.Optional.of(personaEntity));

        PersonaDTO expected = new PersonaDTO();
        when(modelMapper.map(personaEntity, PersonaDTO.class)).thenReturn(expected);

        PersonaDTO actual = personaService.getPersonaByUsuario("testuser");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetPersonaByUsuario_notFound() {

        when(personaRepository.findByUsuario("testuser"))
                .thenReturn(Optional.empty());

        EntityNotFoundException exception =
                assertThrows(EntityNotFoundException.class, () -> {
                    personaService.getPersonaByUsuario("testuser");
                });
        String expectedMessage = String.valueOf(CustomErrorFactory.createNotFoundByUsuario("testuser"));

        assertEquals(expectedMessage, exception.getMessage());

    }

    @Test
    public void testGetAllPersonasWithPagination_success() {

        PersonaEntity persona1 = new PersonaEntity();
        PersonaDTO personaDTO1 = new PersonaDTO();

        PersonaEntity persona2 = new PersonaEntity();
        PersonaDTO personaDTO2 = new PersonaDTO();

        when(personaRepository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(persona1, persona2)));

        when(modelMapper.map(persona1, PersonaDTO.class)).thenReturn(personaDTO1);
        when(modelMapper.map(persona2, PersonaDTO.class)).thenReturn(personaDTO2);

        Page<PersonaDTO> expected = new PageImpl<>(List.of(personaDTO1, personaDTO2));
        Page<PersonaDTO> actual = personaService.getAllPersonasWithPagination(null, null, null, null, 0, 10);

        assertEquals(expected.getContent(), actual.getContent());
    }

}
