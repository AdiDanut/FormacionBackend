package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.controller.dto.StudentDTO;
import com.example.block7crudvalidation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable String id,
                                                        @RequestParam(defaultValue = "simple") String param){
        return ResponseEntity.ok(studentService.getStudentById(id, param));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentService.addStudent(studentDTO));
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/{idEstudiante}/asignar")
    public ResponseEntity<StudentDTO> asignarAsignaturas(@PathVariable String idEstudiante, @RequestBody List<String> idAsignaturas) {
        StudentDTO estudianteAsignado = studentService.asignarAsignatura(idAsignaturas, idEstudiante);
        if (estudianteAsignado != null) {
            return ResponseEntity.ok(estudianteAsignado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idEstudiante}/desasignar")
    public ResponseEntity<StudentDTO> desasignarAsignaturas(@PathVariable String idEstudiante, @RequestBody List<String> idAsignaturas) {
        StudentDTO estudianteDesasignado = studentService.desasignarAsignaturas(idAsignaturas, idEstudiante);
        if (estudianteDesasignado != null) {
            return ResponseEntity.ok(estudianteDesasignado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
