package com.example.block7crudvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.block7crudvalidation.entity.AsignaturaEntity;
import com.example.block7crudvalidation.exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.block7crudvalidation.controller.dto.StudentDTO;
import com.example.block7crudvalidation.entity.PersonaEntity;
import com.example.block7crudvalidation.entity.ProfesorEntity;
import com.example.block7crudvalidation.entity.StudentEntity;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import com.example.block7crudvalidation.repository.StudentRepository;
import com.example.block7crudvalidation.service.impl.StudentServiceImpl;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testAddStudent() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdPersona(1L);

        PersonaEntity persona = new PersonaEntity();

        StudentEntity student = new StudentEntity();

        when(personaRepository.findById(1L)).thenReturn(Optional.of(persona));
        when(modelMapper.map(studentDTO, StudentEntity.class)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO result = studentService.addStudent(studentDTO);

        assertEquals(studentDTO, result);
    }

    @Test
    public void testAddStudent_profesorExists_throwsException() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setIdPersona(1L);

        when(profesorRepository.findByIdPersona(1L)).thenReturn(Optional.of(new ProfesorEntity()));

        assertThrows(UnprocessableEntityException.class, () -> studentService.addStudent(studentDTO));
    }

    @Test
    public void testGetAllStudents() {

        StudentEntity student1 = new StudentEntity();
        StudentDTO studentDTO1 = new StudentDTO();

        StudentEntity student2 = new StudentEntity();
        StudentDTO studentDTO2 = new StudentDTO();

        when(studentRepository.findAll()).thenReturn(List.of(student1, student2));

        when(modelMapper.map(student1, StudentDTO.class)).thenReturn(studentDTO1);
        when(modelMapper.map(student2, StudentDTO.class)).thenReturn(studentDTO2);

        List<StudentDTO> expected = List.of(studentDTO1, studentDTO2);
        assertEquals(expected, studentService.getAllStudents());
    }

    @Test
    public void testUpdateStudent() {

        StudentDTO studentDTO = new StudentDTO();
        StudentEntity updatedStudent = new StudentEntity();

        when(studentRepository.existsById("1")).thenReturn(true);
        when(studentRepository.save(updatedStudent)).thenReturn(updatedStudent);

        when(modelMapper.map(studentDTO, StudentEntity.class)).thenReturn(updatedStudent);
        when(modelMapper.map(updatedStudent, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO result = studentService.updateStudent("1", studentDTO);
        assertEquals(studentDTO, result);

    }

    @Test
    public void testGetStudentById() {

        StudentEntity student = new StudentEntity();
        StudentDTO studentDTO = new StudentDTO();

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);

        Object result = studentService.getStudentById("1", "");
        assertEquals(studentDTO, result);

    }

    @Test
    public void testGetAllStudents_full() {

        StudentEntity student = new StudentEntity();

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        Object result = studentService.getStudentById("1", "full");

        assertEquals(student, result);

    }

    @Test
    public void testGetStudentById_withoutFull() {

        StudentEntity student = new StudentEntity();
        StudentDTO dto = new StudentDTO();

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));
        when(modelMapper.map(student, StudentDTO.class)).thenReturn(dto);

        Object result = studentService.getStudentById("1", "");

        assertEquals(dto, result);

    }

    @Test
    public void testUpdateStudent_notFound() {

        when(studentRepository.existsById("1")).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> studentService.updateStudent("1", new StudentDTO()));

    }

    @Test
    public void testAddStudent_personaNotFound() {

        StudentDTO dto = new StudentDTO();
        dto.setIdPersona(1L);

        when(personaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.addStudent(dto));

    }

    @Test
    public void testDeleteStudent() {
        studentService.deleteStudent("1");
        verify(studentRepository).deleteById("1");
    }

    @Test
    public void testAsignarAsignatura() {

        StudentEntity student = mock(StudentEntity.class);
        when(student.getAsignaturaEntityList()).thenReturn(new ArrayList<>());

        StudentDTO studentDTO = new StudentDTO();

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        when(asignaturaRepository.findAllById(List.of("a1", "a2")))
                .thenReturn(List.of(new AsignaturaEntity(), new AsignaturaEntity()));

        when(studentRepository.save(student)).thenReturn(student);
        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO result = studentService.asignarAsignatura(List.of("a1", "a2"), "1");

        assertEquals(studentDTO, result);
    }


    @Test
    public void testDesasignarAsignaturas() {

        StudentDTO studentDTO = new StudentDTO();

        StudentEntity student = mock(StudentEntity.class);
        when(student.getAsignaturaEntityList()).thenReturn(new ArrayList<>());

        when(studentRepository.findById("1")).thenReturn(Optional.of(student));

        when(asignaturaRepository.findAllById(List.of("a1", "a2")))
                .thenReturn(List.of(new AsignaturaEntity(), new AsignaturaEntity()));

        when(studentRepository.save(student)).thenReturn(student);
        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO result = studentService.desasignarAsignaturas(List.of("a1", "a2"), "1");

        assertEquals(studentDTO, result);
    }

    @Test
    public void testUpdateStudent_notFoundException() {

        when(studentRepository.existsById("1"))
                .thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> studentService.updateStudent("1", new StudentDTO()));

    }

    @Test
    public void testAsignarAsignatura_studentNotFound() {

        when(studentRepository.findById("1"))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.asignarAsignatura(List.of(), "1"));

    }

    @Test
    public void testDesasignarAsignaturas_studentNotFound() {

        when(studentRepository.findById("1"))
                .thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> studentService.asignarAsignatura(List.of(), "1"));

    }

}