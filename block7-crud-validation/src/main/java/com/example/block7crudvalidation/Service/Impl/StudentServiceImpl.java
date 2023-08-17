package com.example.block7crudvalidation.Service.Impl;

import com.example.block7crudvalidation.Controller.DTO.PersonaDTO;
import com.example.block7crudvalidation.Controller.DTO.StudentDTO;
import com.example.block7crudvalidation.Entity.PersonaEntity;
import com.example.block7crudvalidation.Entity.StudentEntity;
import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Factory.CustomErrorFactory;
import com.example.block7crudvalidation.Repository.PersonaRepository;
import com.example.block7crudvalidation.Repository.StudentRepository;
import com.example.block7crudvalidation.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PersonaRepository personaRepository;

    public Object getStudentById(String id, String param) {
        if (param.equals("full")) {
            StudentEntity student = studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            CustomErrorFactory.createNotFoundByUsuario("Persona")));
            return student;
        } else {
            StudentEntity studentEntity = studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            CustomErrorFactory.createNotFoundByUsuario("Persona")));

            return convertToDTO(studentEntity);
        }
    }

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        PersonaEntity personaEntity = personaRepository.findById(studentDTO.getIdPersona())
                .orElseThrow(() -> new EntityNotFoundException(
                        CustomErrorFactory.createNotFound("Persona", studentDTO.getIdPersona()))
                );

        StudentEntity studentEntity = convertToEntity(studentDTO);
        studentEntity.setPersona(personaEntity);

        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return convertToDTO(savedStudent);
    }

    private StudentDTO convertToDTO(StudentEntity student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    private StudentEntity convertToEntity(StudentDTO student) {
        return modelMapper.map(student, StudentEntity.class);
    }

}
