package com.example.block7crudvalidation.Service;

import com.example.block7crudvalidation.Controller.DTO.ProfesorDTO;
import com.example.block7crudvalidation.Controller.DTO.StudentDTO;

public interface StudentService {

    Object getStudentById(String id, String param);

    StudentDTO addStudent(StudentDTO studentDTO);
}
