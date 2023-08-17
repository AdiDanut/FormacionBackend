package com.example.block7crudvalidation.Controller;

import com.example.block7crudvalidation.Controller.DTO.StudentDTO;
import com.example.block7crudvalidation.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable String id,
                                            @RequestParam(defaultValue = "simple") String param){
        return ResponseEntity.ok(studentService.getStudentById(id, param));
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO){
        return ResponseEntity.ok(studentService.addStudent(studentDTO));
    }
}
