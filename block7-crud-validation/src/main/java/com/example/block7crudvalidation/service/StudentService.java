package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.controller.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    Object getStudentById(String id, String param);

    StudentDTO addStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO updateStudent(String id, StudentDTO studentDTO);

    void deleteStudent(String id);

    StudentDTO asignarAsignatura(List<String> idAsignatura, String idEstudiante);
    StudentDTO desasignarAsignaturas(List<String> idAsignaturas, String idEstudiante);
}
