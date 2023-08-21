package com.example.block7crudvalidation.Service.Impl;

import com.example.block7crudvalidation.Controller.DTO.StudentDTO;
import com.example.block7crudvalidation.Entity.AsignaturaEntity;
import com.example.block7crudvalidation.Entity.PersonaEntity;
import com.example.block7crudvalidation.Entity.ProfesorEntity;
import com.example.block7crudvalidation.Entity.StudentEntity;
import com.example.block7crudvalidation.Exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.Exceptions.UnprocessableEntityException;
import com.example.block7crudvalidation.Factory.CustomErrorFactory;
import com.example.block7crudvalidation.Repository.AsignaturaRepository;
import com.example.block7crudvalidation.Repository.PersonaRepository;
import com.example.block7crudvalidation.Repository.ProfesorRepository;
import com.example.block7crudvalidation.Repository.StudentRepository;
import com.example.block7crudvalidation.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public Object getStudentById(String id, String param) {
        if (param.equals("full")) {
            return studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            CustomErrorFactory.createNotFoundByUsuario("Persona")));
        } else {
            StudentEntity studentEntity = studentRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            CustomErrorFactory.createNotFoundByUsuario("Persona")));

            return convertToDTO(studentEntity);
        }
    }

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        if (profesorRepository.findByIdPersona(studentDTO.getIdPersona()).isPresent()) {
            throw new UnprocessableEntityException(CustomErrorFactory.createUserProfesor());
        }

        PersonaEntity personaEntity = personaRepository.findById(studentDTO.getIdPersona())
                .orElseThrow(() -> new EntityNotFoundException(
                        CustomErrorFactory.createNotFound("Persona", studentDTO.getIdPersona()))
                );

        StudentEntity studentEntity = convertToEntity(studentDTO);
        studentEntity.setPersona(personaEntity);

        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return convertToDTO(savedStudent);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        return studentEntities.stream()
                .map(entity -> modelMapper.map(entity, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        if (studentRepository.existsById(id)) {
            StudentEntity studentEntity = convertToEntity(studentDTO);
            studentEntity.setIdStudent(id);

            Optional<PersonaEntity> persona = personaRepository.findById(studentDTO.getIdPersona());
            if (persona.isPresent()){
                studentEntity.setPersona(persona.get());
            }

            ProfesorEntity profesorEntity =
                    studentDTO.getIdProfesor() != null
                    ? profesorRepository.findById(studentDTO.getIdProfesor()).orElseThrow()
                    : null;

                studentEntity.setProfesor(profesorEntity);

            studentEntity = studentRepository.save(studentEntity);
            return convertToDTO(studentEntity);
        } else {
            throw new EntityNotFoundException(CustomErrorFactory.createNotFoundByUsuario("Stundent"));
        }
    }

    @Override
    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO asignarAsignatura(List<String> idAsignaturas, String idEstudiante) {
        Optional<StudentEntity> estudianteOptional = studentRepository.findById(idEstudiante);

        if (estudianteOptional.isPresent()) {
            StudentEntity estudiante = estudianteOptional.get();

            List<AsignaturaEntity> asignaturas = asignaturaRepository.findAllById(idAsignaturas);

            estudiante.getAsignaturaEntityList().addAll(asignaturas);

            return modelMapper.map(studentRepository.save(estudiante), StudentDTO.class);
        } else {
            throw new EntityNotFoundException(CustomErrorFactory.createNullUser());
        }
    }

    @Override
    public StudentDTO desasignarAsignaturas(List<String> idAsignaturas, String idEstudiante) {
        Optional<StudentEntity> estudianteOptional = studentRepository.findById(idEstudiante);

        if (estudianteOptional.isPresent()) {
            StudentEntity estudiante = estudianteOptional.get();

            List<AsignaturaEntity> asignaturasDesasignar = asignaturaRepository.findAllById(idAsignaturas);

            estudiante.getAsignaturaEntityList().removeAll(asignaturasDesasignar);

            return modelMapper.map(studentRepository.save(estudiante), StudentDTO.class);
        } else {
            throw new EntityNotFoundException(CustomErrorFactory.createNullUser());
        }
    }

    private StudentDTO convertToDTO(StudentEntity student) {
        return modelMapper.map(student, StudentDTO.class);
    }

    private StudentEntity convertToEntity(StudentDTO student) {
        return modelMapper.map(student, StudentEntity.class);
    }

}
